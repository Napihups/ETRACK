function display(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                $('#img-gallery').append('<div class="col-md-4"> <img height' + 
                '= "250" width="100%" id="job-image" class="image-panel" src="' + 
                e.target.result + '"/> <div class= "btn-img-delete">' +  
                '<i class="fa fa-times fa-2x" aria-hidden="true"></i></div></div>');
                // $('#job-img')
                //     .attr('src', e.target.result)
                //     .width(150)
                //     .height(200);
            };

            reader.readAsDataURL(input.files[0]);
        }
} 

$('#squarespaceModal').on('hidden.bs.modal', function (e) {
  $(this)
    .find("input,textarea,select")
       .val('')
       .end()
    .find("input[type=checkbox], input[type=radio]")
       .prop("checked", "")
       .end();
    removeImgGallery();
})

function removeImgGallery() {
     $('#img-gallery').empty();       
}

