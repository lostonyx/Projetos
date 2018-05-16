
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
									<th>Nome do Usuario</th>
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
							if(sizeof($trans) > 0){
							    foreach ($trans as $transa){
							        $result = $CI->client_model->select($transa->user_id);
							        $dados = $result[0];
							        $resultp = $CI->plugin_model->select($transa->plugin_id);
							        $dadosp = $resultp[0];
    							    echo '
                                    <tr>
                                        <td>'.$dados->name.'</td>
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
