<?php echo $header; ?><?php echo $column_left; ?>
<div id="content">
<div class="block-header">
        <h2><?php echo $heading_title; ?></h2>
        <ul class="breadcrumb">
            <?php foreach ($breadcrumbs as $breadcrumb) { ?>
            <li><a href="<?php echo $breadcrumb['href']; ?>"><?php echo $breadcrumb['text']; ?></a></li>
            <?php } ?>
        </ul>
    </div>
<div class="container-fluid">
  <div class="card">
    <div class="card-header">
      <h3 class="panel-title"><i class="fa fa-exclamation-triangle"></i> <?php echo $heading_title; ?></h3>
    </div>
    <div class="panel-body">
      <p class="text-center"><?php echo $text_permission; ?></p>
    </div>
  </div>
</div>
<?php echo $footer; ?>