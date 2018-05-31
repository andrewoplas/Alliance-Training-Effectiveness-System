
(function ($) {
    "use strict";

    $(".counter").counterUp({
        delay: 100,
        time: 1000
    });
    
    [].slice.call(document.querySelectorAll('.sttabs')).forEach(function(el) {
        new CBPFWTabs(el);
    });
    
    // Statistics (Charts)
    var colors = ["#F08295","#DC93C2","#ACABE0","#6CC1E2","#41D0C9","#66D99E","#A4D972","#E4D25A","#DB9A4C"];
    $('.question-container').each(function(){
    	var questionContainer = $(this);
    	var questionID = $(this).attr('data-id');
    	console.log(questionID);
    	
    	var questionType = $(this).attr('data-type');
    	var dataContainer = $(this).find('.data-container');
    	
    	if(questionType == 'radiobutton') {
    		var shuffledColors = shuffle(colors.slice()); 
    		var data = {};
    		
    		var legend = {};
    		dataContainer.find('.data-options-container .data-options').each(function(){
    			legend[$(this).attr('value-id')] = $(this).val();
    			data[$(this).attr('value-id')] = 0;
    		});
    		
    		dataContainer.find('.data').each(function(){
    			if($(this).val().length > 0) {
    				data[$(this).val()] += 1;
    			}
    		});
    		
    		var htmlContainer = $('<div class="scale-container"><canvas id="question'+ questionID +'"></canvas></div>');
    		questionContainer.append(htmlContainer);
    		
    		new Chart(document.getElementById("question" + questionID), {
    			type: 'pie',
    		    data: {
    		    	labels: (Object.keys(legend).map(function (key) { return legend[key]; })),
    		    	datasets: [{
	    		        label: "",
	    		        backgroundColor: shuffledColors,
	    		        data: (Object.keys(data).map(function (key) { return data[key]; }))
    		    	}]
    		    },
    		    options: {
    		    	title: {
    		    		display: false,
    		    	},
    		    	responsive: true,
    		        legend: {
    		          position: 'right',
    		        },
    		        tooltips: {
      		    	  callbacks: {
      		    		  label: function(tooltipItem, data) {
	      		    	      //get the concerned dataset
	      		    	      var dataset = data.datasets[tooltipItem.datasetIndex];
	      		    	      //calculate the total of this data set
	      		    	      var total = dataset.data.reduce(function(previousValue, currentValue, currentIndex, array) {
	      		    	        return previousValue + currentValue;
	      		    	      });
	      		    	      //get the current items value
	      		    	      var currentValue = dataset.data[tooltipItem.index];
	      		    	      //calculate the precentage based on the total and current item, also this does a rough rounding to give a whole number
	      		    	      var precentage = Math.floor(((currentValue/total) * 100)+0.5);
	
	      		    	      return precentage + "% ("+ currentValue +")";
  		    		  	 	}
      		    	  	}
    		        } 
		    	},
    		    
    		});
    	} else if (questionType == 'scale') {
    		var htmlContainer = $('<div class="scale-container"><canvas id="question'+ questionID +'"></canvas></div>');
    		questionContainer.append(htmlContainer);
    		
    		var data = [0, 0, 0, 0, 0];
    		dataContainer.find('.data').each(function(){
    			data[parseInt($(this).val()) - 1] += 1;
    		});
    		
    		var ctx = document.getElementById("question" + questionID).getContext('2d');
    		var myChart = new Chart(ctx, {
    		    type: 'bar',
    		    data: {
    		        labels: ["Strongly Agree", "Agree", "Neutral", "Disagree", "Strongly Disagree"],
    		        datasets: [{
    		            data: data,
    		            backgroundColor: [
    		            	'rgba(153, 102, 255, 0.3)',
    		            	'rgba(54, 162, 235, 0.3)',
    		            	'rgba(86, 101, 115, 0.3)',    		                
    		            	'rgba(255, 206, 86, 0.3)',
    		                'rgba(255, 99, 132, 0.3)',
    		            ],
    		            borderColor: [
    		            	'rgba(153, 102, 255, 1)',
    		            	'rgba(54, 162, 235, 1)',
    		            	'rgba(86, 101, 115, 1)',
    		                'rgba(255, 206, 86, 1)',
    		                'rgba(255,99,132,1)',
    		            ],
    		            borderWidth: 1
    		        }]
    		    },
    		    options: {
    		        legend: {
    		        	display: false
    		        },
    		      	tooltips: {
    		        	callbacks: {
	    		          	label: function(tooltipItem) {
	    		            	return tooltipItem.yLabel;
	    		          	}
    		        	}
    		      	},
    		      	scales: {
    		      		yAxes: [{
    		      			ticks: {
    		      				beginAtZero: true,
    		      				callback: function(value) {if (value % 1 === 0) {return value;}}
    		      			}
    		      		}]
    		      	}
		    	}
    		});
    	}
    });
    
    function shuffle(a) {
        var j, x, i;
        for (i = a.length - 1; i > 0; i--) {
            j = Math.floor(Math.random() * (i + 1));
            x = a[i];
            a[i] = a[j];
            a[j] = x;
        }
        return a;
    }
	
})(jQuery);