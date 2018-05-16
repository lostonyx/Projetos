<?php
defined('BASEPATH') OR exit('No direct script access allowed');
?>
<header class="presentation">
		<div class="mid">
			<div class="logo animated bounceInDown">
				<img src="<?php echo asset_url();?>images/logo-o.png" class="img-fluid"
					alt="TintaDev - Logo">
			</div>
			<div class="welcome animated fadeIn">Bem vindo, Cliente!</div>
			<div class="welcome-2 animated fadeIn">
				Você está pronto para ter um servidor todo kibado <span
					class="animated infinite fadeIn">? </span>
			</div>
			<div></div>
			<img src="<?php echo asset_url();?>images/utils/intro-bg.png"
				class="intro-particle" alt="TintaDev - Home">
		</div>
</header>
<div class="content-wrapper">
	<div class="bg">
		<h3 class="text-center">Por que comprar comigo?</h3>
		<br>
		<div class="row">
			<div class="col-md-3">
				<div class="card wow animated zoomIn">
					<img src="<?php echo asset_url();?>images/utils/upgrade.png"
						class="img-fluid" alt="TintaDev - Icon"> <br>
					<h4 class="text-center">Atualizações</h4>
					<p class="text-center">Todos os plugins recebem updates sempre que
						possível</p>
				</div>
			</div>
			<div class="col-md-3">
				<div class="card wow animated zoomIn">
					<img src="<?php echo asset_url();?>images/utils/fast.png"
						class="img-fluid" alt="TintaDev - Icon"> <br>
					<h4 class="text-center">Agilidade</h4>
					<p class="text-center">Suporte, desenvolvimento e entrega rápida</p>
				</div>
			</div>
			<div class="col-md-3">
				<div class="card wow animated zoomIn">
					<img src="<?php echo asset_url();?>images/utils/security.png"
						class="img-fluid" alt="TintaDev - Icon"> <br>
					<h4 class="text-center">Segurança</h4>
					<p class="text-center">Compra segura e plugins totalmente livre de
						malicia</p>
				</div>
			</div>
			<div class="col-md-3">
				<div class="card wow animated zoomIn">
					<img src="<?php echo asset_url();?>images/utils/gift.png"
						class="img-fluid" alt="TintaDev - Icon"> <br>
					<h4 class="text-center">Encomendas</h4>
					<p class="text-center">
						Que tal um plugin totalmente <b>seu</b>? Entre em contato
					</p>
				</div>
			</div>
		</div>
	</div>
	<div class="status">
		<br>
		<div class="row">
			<div class="col-md-4">
				<h4 class="text-center no-margin">
					<i class="ion-plus"></i> DE <span class="wow count">{PLUGINPRONTOS}</span>
					PLUGINS PRONTOS
				</h4>
			</div>
			<div class="col-md-4">
				<h4 class="text-center no-margin">
					<i class="ion-plus"></i> DE <span class="wow count">{CLIENTES}</span>
					CLIENTES
				</h4>
			</div>
			<div class="col-md-4">
				<h4 class="text-center no-margin">
					<i class="ion-plus"></i> DE <span class="wow count">{TOTALDEVENDAS}</span>
					VENDAS
				</h4>
			</div>
		</div>
		<br>
	</div>
	<div class="bg">
		<h3>Meus últimos trabalhos</h3>
		<br>
		<div class="row">
			<div class="col-md-3">
				<div class="plugin">
					<div class="description">
						<img class="img-fluid"
							src="<?php echo asset_url();?>images/utils/plugin.png"
							alt="TintaDev - Plugin">
						<div class="title">{NOMEDOPLUGIN}</div>
					</div>
					<div class="informations">
						<div class="mid">
							<div class="title">{NOMEDOPLUGIN}</div>
							<div class="price">Valor: R${PRECODOPLUGIN}</div>
							onclick="window.location.href='/loja/free'"
							<button onclick="window.location.href='/loja/checkout/NOMEDOPLUGIN'"
								class="waves">COMPRAR</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>