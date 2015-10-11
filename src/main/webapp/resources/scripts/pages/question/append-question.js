function appendQuestion(data) {
	
	var questionId = data['questionId'];
	var questionText = data['questionText'];
	//console.log(questionId+' '+questionText);
	var item = $('<li id="question-'+questionId+'"> <div class="task-title"> <span class="task-title-sp">'+questionText+' </span></div><div class="task-config"><div class="task-config-btn btn-group"><a class="btn btn-xs default" data-toggle="dropdown" data-hover="dropdown" data-close-others="true"> <i class="fa fa-cog"></i><i class="fa fa-angle-down"></i></a><ul class="dropdown-menu pull-right"><li id="add-'+questionId+'"><a id="addButton0" name="addButton'+questionId+'" type="button" onclick="addQuestions('+questionId+')"> <i class="fa fa-plus"></i> Add</a></li><li style="display: none;" id="remove-'+questionId+'"><a id="removeButton'+questionId+'" name="removeButton'+questionId+'" type="button" onclick="removeQuestions('+questionId+')"> <i class="fa fa-times"></i> Remove</a></li><li><a> <i class="fa fa-info"></i> Details</a></li></ul></div></div></li>');
	$('#question-bank').append(item);
}