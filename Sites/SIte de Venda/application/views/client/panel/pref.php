	<header class="intro">
		<div class="mid">
			<div class="container">
				<h3>Preferencias</h3>
			</div>
		</div>
	</header>
	<div class="content-wrapper">
		<div class="blank">
			<div class="row">
			<?php
			 $CI =& get_instance();
			 $CI->load->model('loja_model');
    			foreach ($dados as $dado){
    			    $result = $CI->loja_model->select($dado->id_plugin);
    			    $info = $result[0];
    			    
    			    $arr['id']   = $dado->id_plugin;
    			    $arr['user_id']   = $this->my_auth->get('user', 'id');
    			    $arr['nome'] = $info->nome;
    			    $arr['ip']   = $dado->ip;
    			    $_SESSION['keyinfo'] = $arr;
    			    
    			    echo '
    				<div class="col-md-3">
    					<h3 class="text-center">'.$info->nome.'</h3>
    					<br> <img class="img-fluid"
    						src="'.asset_url().'images/utils/plugin.png"
    						style="max-width: 150px; position: relative; left: 50%; transform: translateX(-50%)">
    					<br>
    				</div>
    				<div class="col-md-6">
    										<div class="bg">
    						<h3>Configuracoes</h3>
    						<br>
                                '.form_open("client/preferencia/$dado->id_plugin").'
    							<div class="row">
    								<div class="col-md-12">
    									<label for="Usuario">Ip Para ser liberado:</label>
    									<input type="text" name="ip" value="'.$dado->ip.'" id="ip" class="form-control" naxlenth="255">
    								</div>
    							</div>
    							<br>
    							<button class="btn btn-success form-control">SALVAR</button>
    			                 '.form_close().'
                                '.form_open("client/gerarkey").'
    							<button class="btn btn-success form-control">GERAR KEY</button>
                                '.form_close().'
                                <br><br>
                                '.form_open("client/plugins").'
    							<button class="btn btn-success form-control">IR /P PAINEL</button>
                                '.form_close().'
    					</div>
    				</div>
                    <div class="col-md-12">
    					<div class="bg">
    						<h3>INFORMACOES</h3>
    						<br>
                                '.show_message().'
    					</div>
    				</div>';	
    			}
    			
			?>
			</div>
		</div>
	</div>