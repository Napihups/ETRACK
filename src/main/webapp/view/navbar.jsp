<nav class="navbar navbar-default main-navbar">
    <div class="container-fluid">
        <div class="navbar-header"><a href="#" class="navbar-brand navbar-link">CUSHWAKE ST OPS</a>
            <button data-toggle="collapse" data-target="#navcol-1" class="navbar-toggle collapsed"><span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button>
        </div>
        <div class="collapse navbar-collapse" id="navcol-1">
            <ul class="nav navbar-nav">
                <li role="presentation" class="active"><a href="#">Dashboard </a></li>
                <li data-toggle="modal" data-target="#squarespaceModal" class=""><a href="#">New Job <i class="fa fa-plus"></i></a></li>
            </ul>
            <ul class="nav navbar-nav right-navbar-list">
                <li role="presentation"><a href="#">${welcomename}</a></li>
                <li role="presentation"><a href="#" id="logoutButton">Logout </a></li>
                <li role="presentation"><a href="#">Account </a></li>
            </ul>
        </div>
    </div>
	</nav>

	
<!-- line modal -->
<div class="modal fade" id="squarespaceModal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
  <div class="modal-dialog modal-new-job">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
			<h3 class="modal-title" id="lineModalLabel">New Job</h3>
		</div>
		<form id ="jobForm">
		<div class="modal-body">
			
            <!-- content goes here -->
              <div class="form-group">
                  <div class ="form-group">
                  <label for="jobheader"> Description </label>
                  <input name ="jobDesciption" type="text" class = "form-control" placeholder="...">
                  </div>
                <label for="exampleInputEmail1"> Any Remarks ?</label>
                <textarea id ="jobRemarks" class="form-control" rows="3" ></textarea>
                <span class="help-block">A longer block of help text that breaks onto a new line and may extend beyond one line.</span>
                  <button type="" class="btn btn-primary">Get Location</button> <i class="map-icon fa fa-map-marker fa-2x" aria-hidden="true"></i>
                </div>
                <div class="form-group">
                  <label for="select" >Request to.</label>
                    <select class="form-control select-user-box" id="reqTo">
                      <option>Jatizso</option>
                      <option>2</option>
                      <option>3</option>
                      <option>4</option>
                      <option>5</option>
                    </select>
                </div>
                <div class="image-panel">
                    <div class="row">
                        <div id ="img-gallery" class="col-md-12">
                        </div>
                    </div>
                 </div>
              <div class="form-group">
                <label for="exampleInputFile">File input</label>
                <input type="file" id="exampleInputFile" onchange="display(this);">
                <p class="help-block">Example block-level help text here.</p>
              </div>
              
          

		</div>
		<div class="modal-footer">
			<div class="btn-group" role="group" aria-label="group button">
				<div class="btn-group btn-delete " role="group">
					<button type="button" id="delImage" class="btn btn-default btn-hover-red" data-dismiss="modal"  role="button">Cancel</button>
				</div>
				<div class="btn-group" role="group">
					<button type="submit" class="btn btn-primary btn-hover-green" data-action="save" role="button">Create 
					<i class="fa fa-plus"></i></button>
				</div>
			</div>
		</div>
		</form>
	</div>
  </div>
</div>