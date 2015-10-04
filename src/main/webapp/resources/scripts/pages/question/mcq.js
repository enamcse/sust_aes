function submitMCQ() {
	var url = '/sustaes/question/create/mcq';
	var options = [];
	for (var i = 1; i <= 5; i++) {
		var opt = {};
		opt["text"] = document.getElementById('mcq-opt-' + i + '-text').value;
		opt["status"] = document.getElementById('mcq-opt-' + i + '-status').checked == true ? true
				: false;
		options.push(opt);
	}
	var question = {};
	question['questionText'] = document.getElementById('mcq-question-text').value;
	question['questionMarks'] = document.getElementById('mcq-marks').value;
	question['requiredTime'] = document.getElementById('mcq-time').value;
	question['courseId.courseId'] = 1;
	question['questionTypeId.questionTypeId'] = 1;
	question['questionTypeId.questionTypeName'] = "mcq";
	question['questionBody'] = JSON.stringify(options);

	$.ajax({
		method : "POST",
		url : url,
		data : question
	}).success(function(msg) {
		console.log('Success: ' + msg);
	});
}