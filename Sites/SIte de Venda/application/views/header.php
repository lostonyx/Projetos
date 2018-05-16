<?php
defined('BASEPATH') or exit('No direct script access allowed');
header('Content-Type: text/html; charset=iso-8859-1');
?>
<!DOCTYPE html>
<html>
<head>
<title>TintaDev - Início</title>
<meta http-equiv="content-type" content="text/html;charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<meta name="og:title" content="TintaDev - Início">
<meta name="og:site_name" content="TintaDev">
<meta name="og:type" content="website">
<meta name="description"
	content="Para o sucesso de um servidor, é preciso ter qualidade. Garanta já plugins de qualidade e tenha seu servidor de Minecraft de sucesso.">
<meta name="keywords"
	content="tintadev, alexhackers, plugins de minecraft, minecraft, plugins para servidor, servidores, plugins, bukkit, minecraft coding, developer, java, spigot">
<meta name="robots" CONTENT="index, follow">
<meta name="author"
	content="M4CH4D0_ ( @jm4ch4d0 ou +55 32 98413-9225 )">
<meta name="theme-color" content="#1976d2">
<link rel='shortcut icon' type='image/x-icon'
	href='<?php echo asset_url();?>images/logo-icon.png' />
<link href="<?php echo asset_url();?>css/bootstrap/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<link
	href="<?php echo asset_url();?>css/bootstrap/bootstrap-grid.min.css"
	rel="stylesheet" type="text/css">
<link
	href="<?php echo asset_url();?>css/bootstrap/bootstrap-reboot.min.css"
	rel="stylesheet" type="text/css">
<link href="<?php echo asset_url();?>css/normalize/normalize.min.css"
	rel="stylesheet" type="text/css">
<link href="<?php echo asset_url();?>css/ionicons/ionicons.min.css"
	rel="stylesheet" type="text/css">
<link href="<?php echo asset_url();?>css/waves/waves.min.css"
	rel="stylesheet" type="text/css">
<link href="<?php echo asset_url();?>css/animate/animate.css"
	rel="stylesheet" type="text/css">
<link href="<?php echo asset_url();?>css/core.min.css" rel="stylesheet"
	type="text/css">
<script async
	src="https://www.googletagmanager.com/gtag/js?id=UA-108599215-1"></script>
<script>
	window.dataLayer = window.dataLayer || [];
	function gtag() {
		dataLayer.push(arguments);
	}
	gtag('js', new Date());
	gtag('config', 'UA-108599215-1');
</script>
</head>
<body>
	<nav class="navbar menu navbar-expand-md">
		<div class="container">
			<a href="/" class="navbar-brand"> <img
				src="<?php echo asset_url();?>images/logo.png" class="img-fluid"
				alt="TintaDev - Logo">
			</a>
			<button class="navbar-toggler waves" type="button"
				data-toggle="collapse" data-target="#menu">
				<span class="ion-navicon"></span>
			</button>
			<div class="navbar-collapse collapse justify-content-stretch"
				id="menu">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item">
					<?php echo anchor('/', '<i class="ion-ios-home-outline"></i>'.'Inicio', array('class' => 'nav-link')); ?>
					
					
					<li class="nav-item dropdown"><a class="nav-link dropdown-toggle"
						href="javascript:void(0)" id="pluginsDropdown"
						data-toggle="dropdown" aria-expanded="false"> <i
							class="ion-ios-lightbulb-outline"></i> Plugins
					</a>
						<div class="dropdown-menu" aria-labelledby="pluginsDropdown">
						<?php echo anchor('/loja/pago', 'Pagos', array('class' => 'dropdown-item')); ?>
						<?php echo anchor('/loja/free', 'Gratuitos', array('class' => 'dropdown-item')); ?>
						</div></li>
					<li class="nav-item private">
					<?php echo anchor('client/', '<i class="ion-ios-person"></i>'.'Área do Cliente', array('class' => 'nav-link waves')); ?>
				</ul>
			</div>
		</div>
	</nav>