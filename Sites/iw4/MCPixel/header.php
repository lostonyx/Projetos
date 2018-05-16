<html>
<head>
    <meta charset="UTF-8">
    <title>MCPixel</title>
    <meta name="description" content="Rede de servidores de Minecraft.">
    <meta name="keywords" content="Servidor, Server, MCPixel, Pixel, PixelPvP, PixelHG, HardcoreGames, KitPvP">
    <meta name="robots" content="index, follow">
    <meta name="author" content="MaxterCreations">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href='https://fonts.googleapis.com/css?family=Oswald:400,700,300' rel='stylesheet' type='text/css'>
    <link rel="icon" type="image/png" href="img/logo.png"/>

    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/stylesheet.css">
    <link rel="stylesheet" href="css/theme.css">
    <link rel="stylesheet" href="css/jquery.dynatable.css">
    <link rel="stylesheet" href="fonts/font-awesome/css/font-awesome.css">
</head>
<body>

<nav class="navbar navbar-default">
    <div class="container">
        <div class="navbar-header">
            <div id="hamburguer-menu" type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar3" aria-expanded="false" id="nav-icon4">
                <span></span>
                <span></span>
                <span></span>
                <span></span>
            </div>

            <div class="header-icon">
                <a href="inicio"><img src="images/logo.png" alt="MaxterCreations - Logo"></a>
            </div>

            <div class="collapse navbar-collapse" id="navbar3">
                <nav class="navbar navbar-default navbar-fixed-top">
                    <div class="container">
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                                <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
                                <span class="icon-bar"></span> <span class="icon-bar"></span>
                            </button>
                            <a class="navbar-brand" href="index.html"><img src="img/logo-small.png"></a>
                        </div>
                        <div class="navbar-collapse collapse" id="navigation">
                            <ul class="nav navbar-nav navbar-right">
                                <?php

                                $url = $_SERVER['REQUEST_URI'];

                                if (strpos($url, "inicio") !== false || ((strpos($url, "inicio") == false && strpos($url, "equipe") == false && strpos($url, "regras") == false && strpos($url, "store") == false))) {
                                    ?><li><a class="current-header-item" href="inicio">Início</a></li></li>
                                <?php } else {
                                    ?><li><a href="inicio">Início</a></li></li>
                                <?php }

                                ?>

                                <li><a href="equipe.php" class="<?php if (strpos($url, "equipe") !== false) { ?>current-header-item<?php } ?>">Equipe</a></li>
                                <li><a href="regras.php" class="<?php if (strpos($url, "regras") !== false) { ?>current-header-item<?php } ?>">Regras</a></li>
                                <li><a href="http://forum.mcpixel.com.br/">Fórum</a></li>
                                <li><a href="store" class="<?php if (strpos($url, "store") !== false) { ?>current-header-item<?php } ?>">Loja</a></li>
                            </ul>
                        </div>
                    </div>
                </nav>
            </div>
        </div>
    </div>
</nav>