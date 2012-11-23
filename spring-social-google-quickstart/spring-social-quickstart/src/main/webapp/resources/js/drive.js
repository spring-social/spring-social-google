function getIcon(mimeType) {
	switch(mimeType) {
	case 'application/vnd.google-apps.document':
		return 'page-white-word';
	case 'application/vnd.google-apps.presentation':
		return 'page-white-powerpoint';
	case 'application/vnd.google-apps.spreadsheet':
		return 'page-white-excel';
	case 'application/vnd.google-apps.drawing':
		return 'page-white-picture';
	case 'application/vnd.google-apps.audio':
		return 'music';
	case 'application/vnd.google-apps.folder':
		return 'folder';
	case 'application/vnd.google-apps.form':
		return 'application-form';
	case 'application/vnd.google-apps.fusiontable':
		return 'table';
	case 'application/vnd.google-apps.photo':
		return 'photo';
	case 'application/vnd.google-apps.script':
		return 'script';
	case 'application/vnd.google-apps.sites':
		return 'page-white-world';
	case 'application/vnd.google-apps.video':
		return 'film';
	case 'application/pdf':
		return 'page-white-acrobat';
	default:
		return 'page-white';
	}
}

$(function() {
	$('#mimeTypeIs').autocomplete({
		minLength: 0,
		source: [
	         'application/vnd.google-apps.document',
	         'application/vnd.google-apps.presentation',
	         'application/vnd.google-apps.spreadsheet',
	         'application/vnd.google-apps.drawing',
	         'application/vnd.google-apps.audio',
	         'application/vnd.google-apps.folder',
	         'application/vnd.google-apps.form',
	         'application/vnd.google-apps.fusiontable',
	         'application/vnd.google-apps.photo',
	         'application/vnd.google-apps.script',
	         'application/vnd.google-apps.sites',
	         'application/vnd.google-apps.video',
	         'application/pdf'
		]
	});
	
	$('.star').click(function(e) {
		var el = $(e.target);
		var starred = !el.hasClass('gray');
		var fileId = el.parents('tr').attr('file-id');
		$.post('starfile', {fileId: fileId, star: !starred}, function() {
			el.toggleClass('gray');
			el.attr('title', starred ? 'Star' : 'Unstar');
		});
	});
	
	$('.trash').click(function(e) {
		var row = $(e.target).parents('tr');
		var nameCell = row.find('.name-cell');
		var trashIcon = row.find('.trash');
		var trashed = nameCell.hasClass('trashed');
		var fileId = row.attr('file-id');
		$.post('trashfile', {fileId: fileId, trash: !trashed}, function() {
			nameCell.toggleClass('trashed');
			trashIcon.toggleClass('ui-silk-bin');
			trashIcon.toggleClass('ui-silk-arrow-turn-left');
			trashIcon.attr('title', trashed ? 'Trash' : 'Untrash');
		});
	});
	
	$('.delete').click(function(e) {
		var row = $(e.target).parents('tr');
		var fileId = row.attr('file-id');
		var fileName = row.attr('file-name');
		bootbox.confirm('This wil permenantly delete "' + fileName + '"', function(result) {
			if(result) {
				$.post('deletefile', {fileId: fileId}, function() {
					row.remove();
				});
			}
		});
	});
});

