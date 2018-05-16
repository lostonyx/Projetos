<!DOCTYPE html>
<html dir="<?php echo $direction; ?>" lang="<?php echo $lang; ?>">
<head>
    <meta charset="UTF-8"/>
    <title><?php echo $title; ?></title>
    <base href="<?php echo $base; ?>"/>
    <?php if ($description) { ?>
    <meta name="description" content="<?php echo $description; ?>"/>
    <?php } ?>
    <?php if ($keywords) { ?>
    <meta name="keywords" content="<?php echo $keywords; ?>"/>
    <?php } ?>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0"/>
    <script type="text/javascript" src="dashboard/view/javascript/jquery/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="dashboard/view/javascript/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="dashboard/view/vendors/bower_components/jquery.nicescroll/jquery.nicescroll.min.js"></script>
    <link href="dashboard/view/javascript/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
    <link href="dashboard/view/stylesheet/app.min.1.css" type="text/css" rel="stylesheet"/>
    <link href="dashboard/view/stylesheet/app.min.2.css" type="text/css" rel="stylesheet"/>
    <link href="dashboard/view/vendors/bower_components/animate.css/animate.min.css" type="text/css" rel="stylesheet"/>
    <link href="dashboard/view/vendors/bower_components/material-design-iconic-font/dist/css/material-design-iconic-font.min.css"
          type="text/css" rel="stylesheet"/>
    <link rel="icon" type="image/png" href="application/resource/images/favicon.png">
    <link href="dashboard/view/javascript/font-awesome/css/font-awesome.min.css" type="text/css" rel="stylesheet"/>
    <link href="dashboard/view/javascript/summernote/summernote.css" rel="stylesheet"/>
    <script type="text/javascript" src="dashboard/view/javascript/summernote/summernote.js"></script>
    <script src="dashboard/view/javascript/jquery/datetimepicker/moment.js" type="text/javascript"></script>
    <script src="dashboard/view/javascript/jquery/datetimepicker/bootstrap-datetimepicker.min.js"
            type="text/javascript"></script>
    <script src="dashboard/view/vendors/bower_components/Waves/dist/waves.min.js"></script>
    <!-- Placeholder for IE9 -->
    <!--[if IE 9 ]>
    <script src="dashboard/view/vendors/bower_components/jquery-placeholder/jquery.placeholder.min.js"></script>
    <![endif]-->
    <script src="dashboard/view/javascript/functions.js"></script>
    <link href="dashboard/view/javascript/jquery/datetimepicker/bootstrap-datetimepicker.min.css" type="text/css"
          rel="stylesheet"
          media="screen"/>
    <link type="text/css" href="dashboard/view/stylesheet/stylesheet.css" rel="stylesheet" media="screen"/>
    <?php foreach ($styles as $style) { ?>
    <link type="text/css" href="<?php echo $style['href']; ?>" rel="<?php echo $style['rel']; ?>"
          media="<?php echo $style['media']; ?>"/>
    <?php } ?>
    <?php foreach ($links as $link) { ?>
    <link href="<?php echo $link['href']; ?>" rel="<?php echo $link['rel']; ?>"/>
    <?php } ?>
    <script src="dashboard/view/javascript/common.js" type="text/javascript"></script>
    <?php foreach ($scripts as $script) { ?>
    <script type="text/javascript" src="<?php echo $script; ?>"></script>
    <?php } ?>
</head>
<body class="sw-toggled<?=isset($class)?' '.$class:""?>">
<?php if ($logged) { ?>
<header id="header">
    <ul class="header-inner">

        <li id="menu-trigger" data-trigger="#sidebar">
            <div class="line-wrap">
                <div class="line top"></div>
                <div class="line center"></div>
                <div class="line bottom"></div>
            </div>
        </li>

        <li class="logo hidden-xs">
            <a href="dashboard/common/dashboard?token=<?=$token?>"><b><?=$store_name?></b> Dashboard</a>
        </li>

        <li class="pull-right">
            <ul class="top-menu">
                <li class="dropdown">
                    <a data-toggle="dropdown" class="tm-settings" href=""></a>
                    <ul class="dropdown-menu dm-icon pull-right">
                        <li>
                            <a href="<?=$store_url?>" target="_blank"><i class="zmdi zmdi-shopping-basket"></i> <?=$store_name?></a>
                        </li>
                        <div class="divider"></div>
                        <li class="hidden-xs">
                            <a data-action="fullscreen" href=""><i class="zmdi zmdi-fullscreen"></i> Toggle
                                Fullscreen</a>
                        </li>
                        <li>
                            <a href="dashboard/common/logout"><i class="zmdi zmdi-lock-open"></i> Logout</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </li>
    </ul>
</header>
<section id="main">
<?php } ?>
