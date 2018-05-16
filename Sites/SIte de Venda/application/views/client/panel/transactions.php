
<nav
	class="navbar navbar-light
bg-faded navbar-static-top dash-navbar-top nb-visible">
	<button class="nb-btn-toggle">
		<span class="fa fa-bars"></span>
	</button>
</nav>
<div class="dash-navbar-left nb-visible">
	<a class="navbar-brand" href="#">Painel</a>
	<p class="nb-nav-title">Olá, <?php

echo $this->my_auth->get('user', 'name');
?></p>
	<ul class="nb-nav">
		<li><a href="/client"> <span class="ion-ios-speedometer-outline"></span>
				<span class="nb-link-text">Dashboard</span>
		</a></li>
		<li><a href="/client/plugins"> <span class="ion-ios-filing-outline"></span>
				<span class="nb-link-text">Meus plugins</span>
		</a></li>
		<li><a href="/client/changelog"> <span
				class="ion-ios-calendar-outline"></span> <span class="nb-link-text">Changelog</span>
		</a></li>
		<li><a href="/client/logs"> <span class="ion-ios-list-outline"></span>
				<span class="nb-link-text">Logs</span>
		</a></li>
		<li><a href="/client/transactions"> <span class="ion-shuffle"></span>
				<span class="nb-link-text">Transações</span>
		</a></li>
		<li><a href="/client/docs"> <span class="ion-ios-medkit-outline"></span>
				<span class="nb-link-text">Documentação</span>
		</a></li>
		<li><a href="/client/logout"> <span class="ion-log-out"></span> <span
				class="nb-link-text">Sair</span>
		</a></li>
	</ul>
</div>
<div class="content-wrap
nb-visible" data-effect="nb-push">
	<div class="container-fluid">
		<div class="page-title">
			Transações <span>Veja o que aconteu no site</span>
		</div>
		<br>
		<div class="row">
			<div class="col-12">
				<div class="card">
					<div class="card-body">
						<table id="datatable" class="table table-hover table-bordered"
							cellspacing="0" width="100%">
							<thead>
								<tr>
									<th>Nome do Plugin</th>
									<th>Data</th>
									<th>Metodo</th>
									<th>Status</th>
								</tr>
							</thead>
							<tbody>
							<?php
							$CI =& get_instance();
							$CI->load->model('client_model');
							$CI->load->model('plugin_model');
							if(sizeof($trans) > 0){
							    foreach ($trans as $transa){
							        $result = $CI->client_model->select($transa->user_id);
							        $dados = $result[0];
							        $resultp = $CI->plugin_model->select($transa->plugin_id);
							        $dadosp = $resultp[0];
    							    echo '
                                    <tr>
                                        <td>'.$dadosp->nome.'</td>
                                        <td>'.$transa->data.'</td>
                    				    <td>'.$transa->gateway.'</td>
                                        <td>'.$transa->status.'</td>
                    				</tr>';
							    }
							}
							else{
							    echo "<h5
									style='text-align: center; position: relative; top: 50%; -webkit-transform: translateY(-50%); -moz-transform: translateY(-50%); -ms-transform: translateY(-50%); -o-transform: translateY(-50%); transform: translateY(-50%);'>Você
									não possui nenhum transacoes no site</h5>";
							}
							?>
							</tbody>
							</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

