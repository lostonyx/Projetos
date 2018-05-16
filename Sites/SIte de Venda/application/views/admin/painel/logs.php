
<div class="content-wrap
nb-visible" data-effect="nb-push">
	<div class="container-fluid">
		<div class="page-title">
			Logs <span>Veja o que aconteu no site</span>
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
									<th>Nome do Usuario</th>
									<th>Descricao</th>
									<th>Tipo</th>
									<th>Data</th>
								</tr>
							</thead>
							<tbody>
							<?php
							$CI =& get_instance();
							$CI->load->model('client_model');
							if(sizeof($logs) > 0){
							    foreach ($logs as $log){
							        $result = $CI->client_model->select($log->id_user);
							        $dados = $result[0];
    							    echo '
                                    <tr>
                                        <td>'.$dados->name.'</td>
                                        <td>'.$log->texto.'</td>
                                        <td>'.$log->tipo.'</td>
                    				    <td>'.$log->data.'</td>
                    				</tr>';
							    }
							}
							else{
							    echo "<h5
									style='text-align: center; position: relative; top: 50%; -webkit-transform: translateY(-50%); -moz-transform: translateY(-50%); -ms-transform: translateY(-50%); -o-transform: translateY(-50%); transform: translateY(-50%);'>Você
									não possui nenhum logs</h5>";
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
