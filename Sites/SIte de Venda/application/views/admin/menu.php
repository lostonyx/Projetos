	<nav
		class="navbar navbar-light
bg-faded navbar-static-top dash-navbar-top nb-visible">
		<button class="nb-btn-toggle">
			<span class="fa fa-bars"></span>
		</button>
	</nav>
	<div class="dash-navbar-left nb-visible">
		<a class="navbar-brand" href="#">Painel Admin</a>
		<p class="nb-nav-title">Olá, <?php echo $this->my_auth->get('admin', 'name');
  ?></p>
		<ul class="nb-nav">
			<li><a href="/admin/admin"> <span class="ion-ios-speedometer-outline"></span>
					<span class="nb-link-text">Dashboard</span>
			</a></li>
			<li><a href="/admin/plugins"> <span class="ion-ios-filing-outline"></span>
					<span class="nb-link-text">Plugins</span>
			</a></li>
			<li><a href="/admin/criadores"> <span class="ion-ios-person"></span>
					<span class="nb-link-text">Criadores</span>
			</a></li>
			<li><a href="/admin/changelog"> <span
					class="ion-ios-calendar-outline"></span> <span class="nb-link-text">Changelog</span>
			</a></li>
			<li><a href="/admin/admin/logs"> <span class="ion-ios-list-outline"></span>
					<span class="nb-link-text">Logs</span>
			</a></li>
			<li><a href="/admin/admin/transactions"> <span class="ion-shuffle"></span>
					<span class="nb-link-text">Transações</span>
			</a></li>
			<li><a href="/admin/admin/docs"> <span class="ion-ios-medkit-outline"></span>
					<span class="nb-link-text">Documentação</span>
			</a></li>
			<li><a href="/admin/admin/logout"> <span class="ion-log-out"></span> <span
					class="nb-link-text">Sair</span>
			</a></li>
		</ul>
	</div>