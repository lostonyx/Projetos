
<div class="content-wrap
nb-visible" data-effect="nb-push">
	<div class="container-fluid">
		<div class="page-title">
			Gerenciar changelog <span>Edite/Remova/Ver um changelog!</span>
		</div>
		<br>
		<div class="row">
			<div class="col-12">
				<div class="card">
					<div class="card-body">
					<h5>
						<i class="ion-ios-folder-outline"></i> GERANCIAR CHANGELOG <br> <span><a href="/admin/changelog/add/">Criar um novo changelog</a></span>
					</h5>
						<h3><?php echo show_message() ?></h3>
						<table id="datatable" class="table table-hover table-bordered"
							cellspacing="0" width="100%">
							<thead>
								<tr>
									<th>Nome do Usuario</th>
									<th>Descricao</th>
									<th>Tipo</th>
									<th>Data</th>
								</tr>
							</thead>
							<tbody>
							<?php
							//echo sizeof($logs);
							if(sizeof($changelog) > 0){
							    foreach ($changelog as $log){
    							    echo '
                                    <tr>
                    				    <td>'.$log->data.'</td>
                                        <td>'.$log->texto.'</td>
                                        <td><a href="/admin/changelog/view/'.$log->id.'">Ver</a></span>
                                        <div></div>
                                        <span><a href="/admin/changelog/del/'.$log->id.'">Deletar</a></span>
                                        </td>
                    				</tr>';
							    }
							}
							else{
							    echo "<h5
									style='text-align: center; position: relative; top: 50%; -webkit-transform: translateY(-50%); -moz-transform: translateY(-50%); -ms-transform: translateY(-50%); -o-transform: translateY(-50%); transform: translateY(-50%);'>Você
									não possui nenhum changelog</h5>";
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
