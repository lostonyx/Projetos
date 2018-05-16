	<div class="content-wrap
nb-visible" data-effect="nb-push">
		<div class="container-fluid">
			<div class="page-title">
				Gerenciar criadores <span>Edite/Remova/Ver um criador!</span>
			</div>
			<br>
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-body">
							<h5>
								<i class="ion-ios-folder-outline"></i> GERANCIAR CRIADOR <br> <span><a href="/admin/criadores/add/">Criar um novo criador</a></span>
							</h5>
							<br>
							<div class="plugins-list">
							<h3><?php echo show_message() ?></h3>
							<div>
                    			<div class="row">
                    				    <div class="col-3">NOME</div>
                                        <div class="col-3">MERCADOPAGO</div>
                                        <div class="col-3">PAGSEGURO</div>
                    				    <div class="col-3">ACOES</div>
                    			</div>		
                    		</div>
							<?php
							//echo sizeof($plugins);
							if(sizeof($criadors) > 0){
							    foreach ($criadors as $criador){
    							    echo '
                                    
                                    <div>
                    				<div class="row">
                    				    <div class="col-3"><span>'.$criador->nome.'</span></div>
                                        <div class="col-3"><span>'.($criador->mercadopago == 0 ? "Desligado" : "Ativado").'</span></div>
                                        <div class="col-3"><span>'.($criador->pagseguro == 0 ? "Desligado" : "Ativado").'</span></div>
                    				    <div class="col-3"><span><a href="/admin/criadores/edit/'.$criador->id.'">Editar</a></span>
                                        <span><a href="/admin/criadores/view/'.$criador->id.'">Ver</a></span>
                                        <span><a href="/admin/criadores/del/'.$criador->id.'">Deletar</a></span>
                                        </div>
                    				</div>
                    				</div>';
							    }
							}
							else{
							    echo "<h5
									style='text-align: center; position: relative; top: 50%; -webkit-transform: translateY(-50%); -moz-transform: translateY(-50%); -ms-transform: translateY(-50%); -o-transform: translateY(-50%); transform: translateY(-50%);'>Você
									não possui nenhum criadores</h5>";
							}
							?>
							
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
