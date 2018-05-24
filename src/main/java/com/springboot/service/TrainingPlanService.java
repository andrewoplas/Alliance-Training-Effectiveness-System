package com.springboot.service;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.primitives.Ints;
import com.springboot.body.EventDay;
import com.springboot.body.Training;
import com.springboot.entities.Attendance;
import com.springboot.entities.Schedule;
import com.springboot.entities.TrainingPlan;
import com.springboot.entities.User;
import com.springboot.entities.UserEvent;
import com.springboot.entities.custom.CustomSchedule;
import com.springboot.repository.custom.TrainingPlanRepository;

@Service
public class TrainingPlanService {
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private TrainingPlanRepository tpRepository;
	
	public List<TrainingPlan> retrieveTrainings() {
		return tpRepository.retrieveTrainings(em);
	}
	
	public TrainingPlan retrieveTraining(String id) {
		try {
			return tpRepository.retrieveTraining(em, Integer.parseInt(id));
		} catch (NumberFormatException ex) {
			return null;
		}
	}
	
	public boolean insertTraining(Training training) {
		boolean result = false;
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");
		
		// Create training
		TrainingPlan tp = new TrainingPlan();
		tp.setTitle(training.getTitle());
		tp.setDescription(training.getDescription());
		tp.setCourseOutline(training.getCourseOutline());
		
		int tpId = tpRepository.insertTraining(em, tp);
		
		if(tpId <= 0) return false;
		
		tp.setId(tpId);		
		
		// Save schedule
		EventDay[] eventDays = training.getCalendar();
		Schedule[] schedule = new Schedule[eventDays.length];
		
		try {
			for(int i=0; i<eventDays.length; i++) {
				schedule[i] = new Schedule();
				schedule[i].setTrainingPlan(tp);
				schedule[i].setDate(dateFormatter.parse(eventDays[i].getDate()));
				schedule[i].setTimeStart(new Time(timeFormatter.parse(eventDays[i].getStartTime()).getTime()));
				schedule[i].setTimeEnd(new Time(timeFormatter.parse(eventDays[i].getEndTime()).getTime()));
				schedule[i].setColor(eventDays[i].getClassName());
			}
			
			tpRepository.insertSchedule(em, schedule);
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		
		// Save User involved
		String[] supervisors = training.getSupervisors();
		String[] facilitators = training.getFacilitators();
		String[] participants = training.getParticipants();
		ArrayList<UserEvent> userEvents = new ArrayList<UserEvent>();
		
		try {
			for(int i=0; i<supervisors.length; i++) {
				UserEvent user = new UserEvent();
				user.setUser(new User(Integer.parseInt(supervisors[i])));
				user.setTrainingPlan(tp);
				user.setRole("Supervisor");
				user.setStatus("pending");
				
				userEvents.add(user);
			}
			
			for(int i=0; i<facilitators.length; i++) {
				UserEvent user = new UserEvent();
				user.setUser(new User(Integer.parseInt(facilitators[i])));
				user.setTrainingPlan(tp);
				user.setRole("Facilitator Internal");
				user.setStatus("pending");
				
				userEvents.add(user);
			}
			
			for(int i=0; i<participants.length; i++) {
				UserEvent user = new UserEvent();
				user.setUser(new User(Integer.parseInt(participants[i])));
				user.setTrainingPlan(tp);
				user.setRole("Participant");
				user.setStatus("pending");
				
				userEvents.add(user);
			}
			
			tpRepository.insertUserEvent(em, userEvents.toArray(new UserEvent[userEvents.size()]));
			result = true;
			
			// Send email to users
//			userEvents = (ArrayList<UserEvent>) retrieveTraining(tpId + "").getUserEvents();
//			for(UserEvent userEvent : userEvents) {
//				// Send Email to this user's email
//				// userEvent.getUser().getEmail();
//			}
			
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
		}
		
		return result;
	}
	
	public boolean editTraining(Training training) {
		boolean result = false;
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");
		
		// Edit training
		TrainingPlan tp = new TrainingPlan();
		tp.setId(Integer.parseInt(training.getId()));
		tp.setTitle(training.getTitle());
		tp.setDescription(training.getDescription());
		tp.setCourseOutline(training.getCourseOutline());
		
		if(!tpRepository.editTraining(em, tp)) return false;
		
		// Edit schedule
		EventDay[] eventDays = training.getCalendar();
		Schedule[] schedule = new Schedule[eventDays.length];
		
		try {
			for(int i=0; i<eventDays.length; i++) {
				schedule[i] = new Schedule();
				schedule[i].setTrainingPlan(tp);
				schedule[i].setDate(dateFormatter.parse(eventDays[i].getDate()));
				schedule[i].setTimeStart(new Time(timeFormatter.parse(eventDays[i].getStartTime()).getTime()));
				schedule[i].setTimeEnd(new Time(timeFormatter.parse(eventDays[i].getEndTime()).getTime()));
				schedule[i].setColor(eventDays[i].getClassName());
			}
			
			tpRepository.editSchedule(em, schedule, tp.getId());
		} catch (ParseException ex) {
			return false;
		}
		
		// Save User involved
		String[] supervisors = training.getSupervisors();
		String[] facilitators = training.getFacilitators();
		String[] participants = training.getParticipants();
		ArrayList<Integer> userIDS = new ArrayList<Integer>();
		ArrayList<UserEvent> userEvents = new ArrayList<UserEvent>();
		
		try {
			for(int i=0; i<supervisors.length; i++) {
				UserEvent user = new UserEvent();
				user.setUser(new User(Integer.parseInt(supervisors[i])));
				user.setTrainingPlan(tp);
				user.setRole("Supervisor");
				user.setStatus("pending");
				
				userIDS.add(user.getUser().getId());
				userEvents.add(user);
			}
			
			for(int i=0; i<facilitators.length; i++) {
				UserEvent user = new UserEvent();
				user.setUser(new User(Integer.parseInt(facilitators[i])));
				user.setTrainingPlan(tp);
				user.setRole("Facilitator Internal");
				user.setStatus("pending");
				
				userIDS.add(user.getUser().getId());
				userEvents.add(user);
			}
			
			for(int i=0; i<participants.length; i++) {
				UserEvent user = new UserEvent();
				user.setUser(new User(Integer.parseInt(participants[i])));
				user.setTrainingPlan(tp);
				user.setRole("Participant");
				user.setStatus("pending");
				
				userIDS.add(user.getUser().getId());
				userEvents.add(user);
			}
			
			tpRepository.editUserEvent(em, tp.getId(), userEvents.toArray(new UserEvent[userEvents.size()]), userIDS);
			result = true;
		} catch (NumberFormatException ex) {
			return false;
		}
		
		return result;
	}
	
	public boolean deleteTraining(String id) {
		boolean result = false;
		
		result = tpRepository.removeTraining(em, Integer.parseInt(id));
		
		return result;
	}
	
	public List<CustomSchedule> retrieveTrainingCustomSchedules() {
		List<TrainingPlan> trainings = tpRepository.retrieveTrainings(em);
		List<CustomSchedule> schedules = new ArrayList<CustomSchedule>();
		
		if(trainings.size() > 0) {
			DateFormat dateFormatter = new SimpleDateFormat("yyyy:MM:dd");
			DateFormat timeFormatter = new SimpleDateFormat("HH:mm");
			
			for(TrainingPlan training: trainings) {
				for(Schedule schedule : training.getSchedules()) {
					CustomSchedule cs = new CustomSchedule();
					cs.setId(training.getId());
					cs.setTitle(training.getTitle());
					cs.setDate(dateFormatter.format(schedule.getDate()));
					cs.setTimeStart(timeFormatter.format(schedule.getTimeStart()));
					cs.setTimeEnd(timeFormatter.format(schedule.getTimeEnd()));
					cs.setClassName(schedule.getColor());
					
					schedules.add(cs);
				}
			}
		}
		
		return schedules;
	}
	
	public List<User> retrieveTrainingPeople(TrainingPlan training, String position) {
		try {			
			if(training != null) {
				List<UserEvent> userEvents = training.getUserEvents(); 
				List<User> participants = new ArrayList<User>();
				
				for(UserEvent userEvent : userEvents) {
					if(userEvent.getRole().contains(position)) {
						User user = userEvent.getUser();
						user.setUserEventID(userEvent.getId());
						participants.add(user);
					}
				}
				
				// Sort
				Collections.sort(participants, new Comparator<User>() {
				    @Override
				    public int compare(User a, User b) {
				        // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
				        return a.getName().compareTo(b.getName());
				    }
				});
				
				return participants;
			}					
		} catch (NumberFormatException ex) { return null; }
		
		return null;
	}

	public List<Schedule> sortSchedule(List<Schedule> schedules) {
		
		Collections.sort(schedules, new Comparator<Schedule>() {
		    @Override
		    public int compare(Schedule lhs, Schedule rhs) {
		        // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
		        return lhs.getDate().before(rhs.getDate()) ? -1 : (lhs.getDate().after(rhs.getDate())) ? 1 : 0;
		    }
		});
		
		return schedules;
	}

	public void insertAttendance(String trainingId, String ids, String time, String date) {
		try {
			SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");
			Time timeIn = new Time(timeFormatter.parse(time).getTime());
			Date timeInDate = dateFormatter.parse(date);
			int[] intArray = Arrays.stream(ids.split(","))
				    .mapToInt(Integer::parseInt)
				    .toArray();
			int training = Integer.parseInt(trainingId);
			
			List<Attendance> attendances = new ArrayList<Attendance>();
			for(int i=0; i<intArray.length; i++) {
				Attendance attendance = new Attendance();
				attendance.setDate(timeInDate);
				attendance.setTimeIn(timeIn);
				attendance.setTrainingPlan(new TrainingPlan(training));
				attendance.setUser(new User(intArray[i]));
				
				attendances.add(attendance);
			}
			
			tpRepository.insertAttendance(em, attendances);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public String insertTimeOutAttendance(String trainingId, String ids, String time, String date) {
		try {
			int[] intArray = Arrays.stream(ids.split(","))
				    .mapToInt(Integer::parseInt)
				    .toArray();
			int training = Integer.parseInt(trainingId);
			SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
			Date timeOutDate = dateFormatter.parse(date);
			
			if(!tpRepository.checkHasTimeIn(em, (List<Integer>) Ints.asList(intArray), training, timeOutDate)) {
				return "timein_violation";
			}
			
			SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");
			Time timeOut = new Time(timeFormatter.parse(time).getTime());
			
			List<Attendance> attendances = new ArrayList<Attendance>();
			for(int i=0; i<intArray.length; i++) {
				Attendance attendance = new Attendance();
				attendance.setDate(timeOutDate);
				attendance.setTimeOut(timeOut);
				attendance.setTrainingPlan(new TrainingPlan(training));
				attendance.setUser(new User(intArray[i]));
				
				attendances.add(attendance);
			}
			
			tpRepository.insertTimeOutAttendance(em, attendances);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}

	public void insertAbsentAttendance(String trainingId, String id, String date) {
		try {
			Attendance attendance = new Attendance();
			attendance.setUser(new User(Integer.parseInt(id)));
			attendance.setTrainingPlan(new TrainingPlan(Integer.parseInt(trainingId)));
			
			SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");
		    Time emptyTime = new Time(timeFormatter.parse("00:00:01").getTime());
		    Date timeDate = dateFormatter.parse(date);
		    
			attendance.setTimeIn(emptyTime);
			attendance.setTimeOut(emptyTime);
			attendance.setDate(timeDate);
			
			tpRepository.insertAbsentAttendance(em, attendance);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void resetAttendance(String trainingId, String id, String date) {
		try {
			SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		    Date timeDate = dateFormatter.parse(date);
			
			Attendance attendance = new Attendance();
			attendance.setUser(new User(Integer.parseInt(id)));
			attendance.setTrainingPlan(new TrainingPlan(Integer.parseInt(trainingId)));
			attendance.setDate(timeDate);
						
			tpRepository.resetAttendance(em, attendance);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	
	
}