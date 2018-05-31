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
			ex.printStackTrace();
			
			return null;
		}
	}
	
	public List<TrainingPlan> retrieveTrainingByQuarter(int quarter) {
		List<TrainingPlan> trainings = null;
		
		int startMonth = 0;
		int endMonth = 0;
		
		switch(quarter) {
			case 1 : startMonth = 1;
					 endMonth = 3;
					 break;
			case 2 : startMonth = 4;
					 endMonth = 6;
					 break;
			case 3 : startMonth = 7;
					 endMonth = 9;
					 break;
			case 4 : startMonth = 10;
					 endMonth = 12;
					 break;
		}
		
		trainings = tpRepository.retrieveQuarter(em, startMonth, endMonth);

		return trainings;
	}
	
	public List<List<UserEvent>> retrieveTrainingByQuarter() {
		List<UserEvent> userEvents1Q = retrieveUserEvents(retrieveTrainingByQuarter(1));
		List<UserEvent> userEvents2Q = retrieveUserEvents(retrieveTrainingByQuarter(2));
		List<UserEvent> userEvents3Q = retrieveUserEvents(retrieveTrainingByQuarter(3));
		List<UserEvent> userEvents4Q = retrieveUserEvents(retrieveTrainingByQuarter(4));
		
		List<List<UserEvent>> userEventsList = new ArrayList<List<UserEvent>>();
			userEventsList.add(userEvents1Q);
			userEventsList.add(userEvents2Q);
			userEventsList.add(userEvents3Q);
			userEventsList.add(userEvents4Q);
			
		return userEventsList;
	}
	
	public List<UserEvent> retrieveUserEvents(List<TrainingPlan> trainings) {		
		List<UserEvent> userEvents = new ArrayList<UserEvent>();
		
		for(TrainingPlan training : trainings) {
			userEvents.addAll(tpRepository.retrieveUserEvent(em, training));
		}
		
		return userEvents;
	}
	
	public List<UserEvent> retrieveUserEvent(int userID) {
		return tpRepository.retrieveUserEvent(em, userID) ;
	}
	
	public List<UserEvent> retrieveUserEvents() {
		return tpRepository.retrieveUserEvent(em);
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
			ex.printStackTrace();
			
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
			ex.printStackTrace();
			
			return false;
		}
		
		return result;
	}
	
	public boolean deleteTraining(String id) {
		boolean result = false;
		
		try {
			result = tpRepository.removeTraining(em, Integer.parseInt(id));
		} catch (Exception ex) {
			result = false;
		}
		
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
	
	public List<User> retrieveTrainingPeople(TrainingPlan training, String position, boolean approvedOnly) {
		try {			
			if(training != null) {
				List<UserEvent> userEvents = training.getUserEvents(); 
				List<User> participants = new ArrayList<User>();
				
				for(UserEvent userEvent : userEvents) {
					if(approvedOnly && !userEvent.getStatus().equals("approved")) continue;
					
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
				        return a.getName().compareToIgnoreCase(b.getName());
				    }
				});
				
				return participants;
			}					
		} catch (NumberFormatException ex) { return null; }
		
		return null;
	}
	
	public List<UserEvent> retrieveTrainingUserEvent(TrainingPlan training, String position, boolean approvedOnly) {
		try {			
			if(training != null) {
				List<UserEvent> userEvents = training.getUserEvents(); 
				List<UserEvent> filteredUserEvents = new ArrayList<UserEvent>();
				
				for(UserEvent userEvent : userEvents) {
					if(approvedOnly && !userEvent.getStatus().equals("approved")) continue;
					
					if(userEvent.getRole().contains(position)) {
						filteredUserEvents.add(userEvent);
					}
				}
				
				
				return filteredUserEvents;
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

	public boolean insertAttendance(String trainingId, String ids, String time, String date) {
		try {
			SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");
			SimpleDateFormat timeFormatterSeconds = new SimpleDateFormat("HH:mm:ss");
			
			Time timeIn = new Time(timeFormatter.parse(time).getTime());
			Time absent = new Time(timeFormatterSeconds.parse("00:00:01").getTime());
			Date timeInDate = dateFormatter.parse(date);
			int[] intArray = Arrays.stream(ids.split(","))
				    .mapToInt(Integer::parseInt)
				    .toArray();
			int training = Integer.parseInt(trainingId);
			
			// Insert attendance
			for(int i=0; i<intArray.length; i++) {
				Attendance attendance = new Attendance();
				attendance.setDate(timeInDate);
				attendance.setTimeIn(timeIn);
				attendance.setTrainingPlan(new TrainingPlan(training));
				attendance.setUser(new User(intArray[i]));
				
				tpRepository.insertAttendance(em, attendance, absent);
			}
			
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			
			return false;
		}
	}
	
	public String insertTimeOutAttendance(String trainingId, String ids, String time, String date) {
		try {
			int[] intArray = Arrays.stream(ids.split(","))
				    .mapToInt(Integer::parseInt)
				    .toArray();
			int training = Integer.parseInt(trainingId);
			SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat timeFormatterSeconds = new SimpleDateFormat("HH:mm:ss");
			SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");
			
			Time absent = new Time(timeFormatterSeconds.parse("00:00:01").getTime());
			Time timeOut = new Time(timeFormatter.parse(time).getTime());
			Date timeOutDate = dateFormatter.parse(date);
			
			// Check if has time in
			if(!tpRepository.checkHasTimeIn(em, (List<Integer>) Ints.asList(intArray), training, timeOutDate, absent)) {
				return "timein_violation";
			}
			
			// Check if valid time out 
			if(!tpRepository.checkIfBeforeTimeIn(em, (List<Integer>) Ints.asList(intArray), training, timeOutDate, timeOut)) {
				return "time_difference_violation";
			}
			
			// Update Timeout attendance
			for(int i=0; i<intArray.length; i++) {
				Attendance attendance = new Attendance();
				attendance.setDate(timeOutDate);
				attendance.setTimeOut(timeOut);
				attendance.setTrainingPlan(new TrainingPlan(training));
				attendance.setUser(new User(intArray[i]));
				
				tpRepository.insertTimeOutAttendance(em, attendance);
			}
			
		} catch (ParseException ex) {
			ex.printStackTrace();
			
			return "error";
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
			
			return "error";
		} catch (Exception ex) {
			ex.printStackTrace();
			
			return "error";
		}
		
		return null;
	}

	public boolean insertAbsentAttendance(String trainingId, String id, String date) {
		try {
			
			SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");
		    Time emptyTime = new Time(timeFormatter.parse("00:00:01").getTime());
		    Date timeDate = dateFormatter.parse(date);
		    
		    // Create attendance entity
			Attendance attendance = new Attendance();
			attendance.setUser(new User(Integer.parseInt(id)));
			attendance.setTrainingPlan(new TrainingPlan(Integer.parseInt(trainingId)));		
			attendance.setTimeIn(emptyTime);
			attendance.setTimeOut(emptyTime);
			attendance.setDate(timeDate);
			
			// Insert attendance entity
			tpRepository.insertAbsentAttendance(em, attendance);
			
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			
			return false;
		}
	}
	
	public boolean resetAttendance(String trainingId, String id, String date) {
		try {
			SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		    Date timeDate = dateFormatter.parse(date);
			
		    // Create the attendance entity
			Attendance attendance = new Attendance();
			attendance.setUser(new User(Integer.parseInt(id)));
			attendance.setTrainingPlan(new TrainingPlan(Integer.parseInt(trainingId)));
			attendance.setDate(timeDate);
			
			// Update the attendance entity
			tpRepository.resetAttendance(em, attendance);
			
			return true;
		} catch (ParseException ex) {
			ex.printStackTrace();
			
			return false;
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
			
			return false;
		} catch (Exception ex) {
			ex.printStackTrace();
			
			return false;
		}
	}
	

	public int[] getTrainingStatus(TrainingPlan training) {
		List<UserEvent> userEvents = training.getUserEvents();
		int[] status = new int[3];
		String statusTemp = null;
		
		for(UserEvent userEvent : userEvents) {
			statusTemp = userEvent.getStatus();
			
			if(statusTemp.equals("approved")) {
				status[0] += 1;
			} else if(statusTemp.equals("declined")) {
				status[1] += 1;
			} else if(statusTemp.equals("pending")) {
				status[2] += 1;
			}
		}
		
		return status;
	}
	
}