<?php echo $header; ?>
<div class="container">
    <ul class="breadcrumb">
        <?php foreach ($breadcrumbs as $breadcrumb) { ?>
        <li><a href="<?php echo $breadcrumb['href']; ?>"><?php echo $breadcrumb['text']; ?></a></li>
        <?php } ?>
    </ul>
    <?php if ($success) { ?>
    <div class="alert alert-success"><i class="fa fa-check-circle"></i> <?php echo $success; ?></div>
    <?php } ?>
    <div class="row"><?php echo $column_left; ?>
        <?php if ($column_left && $column_right) { ?>
        <?php $class = 'col-sm-6'; ?>
        <?php } elseif ($column_left || $column_right) { ?>
        <?php $class = 'col-sm-9'; ?>
        <?php } else { ?>
        <?php $class = 'col-sm-12'; ?>
        <?php } ?>
        <div id="content" class="<?php echo $class; ?>"><?php echo $content_top; ?>
            <div class="col col-sm-6">
                <h2><?php echo $text_my_account; ?></h2>
                <ul class="list-unstyled">
                    <li><a href="<?php echo $edit; ?>"><i class="fa fa-edit"></i> <?php echo $text_edit; ?></a></li>
                    <li><a href="<?php echo $password; ?>"><i class="fa fa-key"></i> <?php echo $text_password; ?></a></li>
                    <li><a href="<?php echo $address; ?>"><i class="fa fa-truck"></i> <?php echo $text_address; ?></a></li>
                </ul>
            </div>
            <div class="col col-sm-6">
            <h2><?php echo $text_my_orders; ?></h2>
            <ul class="list-unstyled">
                <li><a href="<?php echo $order; ?>"><i class="fa fa-list"></i> <?php echo $text_order; ?></a></li>
                <li><a href="<?php echo $transaction; ?>"><i class="fa fa-money"></i> <?php echo $text_transaction; ?></a></li>
                <li><a href="<?php echo $recurring; ?>"><i class="fa fa-refresh"></i> <?php echo $text_recurring; ?></a></li>
            </ul>
            </div>
            <div class="col col-sm-6">
            <h2><?php echo $text_my_newsletter; ?></h2>
            <ul class="list-unstyled">
                <li><a href="<?php echo $newsletter; ?>"><i class="fa fa-mail-forward"></i> <?php echo $text_newsletter; ?></a></li>
            </ul>
            </div>
            <?php echo $content_bottom; ?></div>
        <?php echo $column_right; ?></div>
</div>
<?php echo $footer; ?>
