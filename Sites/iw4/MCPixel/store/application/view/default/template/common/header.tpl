<!DOCTYPE html><!--[if IE]><![endif]--><!--[if IE 8 ]><html dir="<?=$direction?>" lang="<?=$lang?>" class="ie8"><![endif]--><!--[if IE 9 ]><html dir="<?=$direction?>" lang="<?=$lang?>" class="ie9"><![endif]--><!--[if (gt IE 9)|!(IE)]><!--><html dir="<?=$direction?>" lang="<?=$lang?>">    <!--<![endif]-->    <head>        <meta charset="UTF-8">        <meta name="viewport" content="width=device-width, initial-scale=1">        <meta http-equiv="X-UA-Compatible" content="IE=edge">        <title><?=$title?></title>        <base href="<?=$base?>">        <?php if ($description) { ?>        <meta name="description" content="<?=$description?>">        <?php } ?>        <?php if ($keywords) { ?>        <meta name="keywords" content= "<?=$keywords?>">        <?php } ?>        <script src="application/resource/javascript/jquery/jquery.js" type="text/javascript"></script>        <!--<link href="application/resource/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">-->        <link href="application/resource/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet" media="screen">        <script src="application/resource/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>        <link href="application/resource/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">        <link href='https://fonts.googleapis.com/css?family=Oswald:400,700,300' rel='stylesheet' type='text/css'>        <link href="application/view/default/stylesheet/stylesheet.css" rel="stylesheet">        <link href="application/view/default/stylesheet/theme.css" rel="stylesheet">        <link rel="icon" type="image/png" href="application/resource/images/favicon.png">        <?php foreach ($styles as $style) { ?>        <link href="<?=$style['href']?>" type="text/css" rel="<?=$style['rel']?>" media="<?=$style['media']?>">        <?php } ?>        <script src="application/resource/javascript/common.js" type="text/javascript"></script>        <?php foreach ($links as $link) { ?>        <link href="<?=$link['href']?>" rel="<?=$link['rel']?>">        <?php } ?>        <?php foreach ($scripts as $script) { ?>        <script src="<?=$script?>" type="text/javascript"></script>        <?php } ?>        <?php foreach ($analytics as $analytic) { ?>        <?=$analytic?>        <?php } ?>    </head>    <body class="<?=$class?>">        <nav class="navbar navbar-custom nav-dark navbar-fixed-top" role="navigation">            <div class="container">                <!-- Brand and toggle get grouped for better mobile display -->                <div class="navbar-header">                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#top-nav">                        <span class="sr-only">Toggle navigation</span>                        <span class="icon-bar"></span>                        <span class="icon-bar"></span>                        <span class="icon-bar"></span>                    </button>                    <a class="navbar-brand" href="<?=$base?>"><img src="application/resource/images/logo-small.png" alt="Logo"></a>                </div>                <div class="collapse navbar-collapse" id="user-nav">                    <ul class="nav navbar-nav navbar-right">                        <li><a href="checkout/view"><span class="label label-nav label-custom-icon"><i class="fa fa-shopping-basket"></i></span> CARRINHO (<?=$cart['total']?> <?=$cart['text']?>)</a></li>                        <?=$currency?>                        <?php if ($logged) { ?>                        <li><a href="<?=$base?>account/account"><span class="label label-nav label-custom-icon"><i class="fa fa-user"></i></span> <?=$text_account?></a></li>                        <li class="item-custom-red"><a href="<?=$base?>account/logout"><span class="label label-nav label-custom-icon label-custom-red"><i class="fa fa-unlock"></i></span> <?=$customer?>, <?=$text_logout?>?</a></li>                        <?php } else { ?>                        <li class="item-custom-green"><a href="<?=$base?>account/login"><span class="label label-nav label-custom-icon label-custom-green"><i class="fa fa-user"></i></span> <?=$text_login?></a></li>                        <?php } ?>                    </ul>                </div><!-- /.navbar-collapse -->            </div><!-- /.container-fluid -->        </nav>        <div class="navbar-fixer"></div>        <nav class="navbar navbar-custom nav-main">            <div class="container">                <div class="navbar-header">                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">                        <span class="sr-only">Toggle navigation</span>                        <span class="icon-bar"></span>                        <span class="icon-bar"></span>                        <span class="icon-bar"></span>                    </button>                </div>                <div id="navbar" class="navbar-collapse collapse">                    <ul class="nav navbar-nav">                        <?php foreach($categories as $category) { ?>                        <li<?=($category['active'])?" class=\"active\"":""?>><a href="<?=$category['href']?>"><?=$category['name']?></a></li>                        <?php } ?>                    </ul>                    <ul class="nav navbar-nav navbar-right">                        <li>                            <form class="navbar-form" role="search" action="common/home">                                <div class="form-group">                                    <input type="hidden" name="category" value="<?=$this->registry->get("current")?>">                                    <input type="text" name="q" class="form-control" value="<?=chk_array($_GET, 'q')?>" placeholder="Pesquise produtos digitando o nome aqui..">                                </div>                                <button type="submit" class="btn btn-dark"><i class="fa fa-search"></i></button>                            </form>                        </li>                    </ul>                </div><!--/.nav-collapse -->            </div><!--/.container-fluid -->        </nav>