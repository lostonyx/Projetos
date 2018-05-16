<?php echo $header; ?><?php echo $column_left; ?>
<div id="content">
  <div class="block-header">
    <div class="pull-right">
      <button type="submit" form="form-featured" data-toggle="tooltip" title="<?php echo $button_save; ?>" class="btn btn-primary"><i class="fa fa-save"></i></button>
      <a href="<?php echo $cancel; ?>" data-toggle="tooltip" title="<?php echo $button_cancel; ?>" class="btn btn-default"><i class="fa fa-reply"></i></a></div>
    <h2><?php echo $heading_title; ?></h2>
    <ul class="breadcrumb">
      <?php foreach ($breadcrumbs as $breadcrumb) { ?>
      <li><a href="<?php echo $breadcrumb['href']; ?>"><?php echo $breadcrumb['text']; ?></a></li>
      <?php } ?>
    </ul>
  </div>
  <div class="container-fluid">
    <?php if ($error_warning) { ?>
    <div class="alert alert-danger"><i class="fa fa-exclamation-circle"></i> <?php echo $error_warning; ?>
      <button type="button" class="close" data-dismiss="alert">&times;</button>
    </div>
    <?php } ?>
    <div class="card">
      <div class="card-header">
        <h3 class="panel-title"><i class="fa fa-pencil"></i> <?php echo $text_edit; ?></h3>
      </div>
      <div class="panel-body">
        <form action="<?php echo $action; ?>" method="post" enctype="multipart/form-data" id="form-featured" class="form-horizontal">

          <div class="form-group required">
            <label class="col-sm-2 control-label" for="input-name"><span data-toggle="tooltip" title="<?php echo $help_name; ?>"><?php echo $entry_name; ?></span></label>
            <div class="col-sm-10">
              <div class="fg-line">
                <input type="text" name="featured_name" value="<?php echo $featured_name; ?>" placeholder="<?php echo $entry_name; ?>" id="input-name" class="form-control" />
              </div>
              <?php if ($error_featured_name) { ?>
              <div class="text-danger"><?php echo $error_featured_name; ?></div>
              <?php } ?>
            </div>
          </div>

          <div class="form-group required">
            <label class="col-sm-2 control-label" for="input-limit"><span data-toggle="tooltip" title="<?php echo $help_limit; ?>"><?php echo $entry_limit; ?></span></label>
            <div class="col-sm-10">
              <div class="fg-line">
                <input type="text" name="featured_limit" value="<?php echo $featured_limit; ?>" placeholder="<?php echo $entry_limit; ?>" id="input-limit" class="form-control" />
              </div>
              <?php if ($error_featured_limit) { ?>
              <div class="text-danger"><?php echo $error_featured_limit; ?></div>
              <?php } ?>
            </div>
          </div>

          <div class="form-group">
            <label class="col-sm-2 control-label" for="input-status"><?php echo $entry_status; ?></label>
            <div class="col-sm-10">
              <select name="featured_status" id="input-status" class="form-control">
                <?php if ($featured_status) { ?>
                <option value="2" selected="selected"><?php echo $text_enabled; ?></option>
                <option value="0"><?php echo $text_disabled; ?></option>
                <?php } else { ?>
                <option value="2"><?php echo $text_enabled; ?></option>
                <option value="0" selected="selected"><?php echo $text_disabled; ?></option>
                <?php } ?>
              </select>
            </div>
          </div>

        </form>
      </div>
    </div>
  </div>
</div>
<?php echo $footer; ?>