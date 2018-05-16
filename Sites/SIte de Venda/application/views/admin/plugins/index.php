
<div class="content-wrap
nb-visible" data-effect="nb-push">
	<div class="container-fluid">
		<div class="page-title">
			Gerenciar plugins <span>Edite/Remova/Ver um plugin!</span>
		</div>
		<br>
		<div class="row">
			<div class="col-12">
				<div class="card">
					<div class="card-body">
					<h5>
						<i class="ion-ios-folder-outline"></i> GERANCIAR PLUGINS <br> <span><a href="/admin/plugins/add/">Criar um novo plugin</a></span>
					</h5>
					<table id="datatable" class="table table-hover table-bordered"
							cellspacing="0" width="100%">
							<thead>
								<tr>
									<th>Plugin</th>
									<th>Valor</th>
									<th>Tipo</th>
									<th>Acoes</th>
								</tr>
							</thead>
							<tbody>
							<?php
							//echo sizeof($plugins);
							if(sizeof($plugins) > 0){
							    foreach ($plugins as $plugin){
    							    echo '
                                    
                                    <tr>
                    				    <td>'.$plugin->nome.'</td>
                                        <td>'.($plugin->preco == 0 ? "Graituto" : "R$ ".$plugin->preco).'</td>
                                        <td>'.($plugin->tipo == 0 ? "ReflectionLoad" : "OneClass").'</td>
                    				    <td><span><a href="/admin/plugins/edit/'.$plugin->id.'">Editar</a></span>
                                        <span><a href="/admin/plugins/view/'.$plugin->id.'">Ver</a></span>
                                        <span><a href="/admin/plugins/del/'.$plugin->id.'">Deletar</a></span>
                                        </td>
                    				</tr>';
							    }
							}
							else{
							    echo "<h5
									style='text-align: center; position: relative; top: 50%; -webkit-transform: translateY(-50%); -moz-transform: translateY(-50%); -ms-transform: translateY(-50%); -o-transform: translateY(-50%); transform: translateY(-50%);'>Você
									não possui nenhum plugin</h5>";
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
