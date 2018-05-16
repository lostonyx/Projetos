<div class="card">
  <div class="card-header"><h3 class="panel-title"><?php echo $heading_title; ?> <span class="pull-right">
    <?php if ($percentage > 0) { ?>
    <i class="fa fa-caret-up"></i>
        <?php } elseif ($percentage < 0) { ?>
        <i class="fa fa-caret-down"></i>
        <?php } ?>
        <?php echo $percentage; ?>%</span></h3></div>
  <div class="panel-body">
      <h2><i class="fa fa-credit-card"></i><span class="pull-right"><?php echo $total; ?></i></span></h2>
  </div>
  <div class="panel-footer"><a href="<?php echo $sale; ?>"><?php echo $text_view; ?></a></div>
</div>
