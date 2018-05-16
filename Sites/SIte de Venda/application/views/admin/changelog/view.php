	<header class="intro">
		<div class="mid">
			<div class="container">
				<h3>Changelog / Ver</h3>
			</div>
		</div>
	</header>
	<div class="content-wrapper">
		<div class="blank">
			<div class="row">
			<?php
    			foreach ($logs as $log){
    			    echo '
    				<div class="col-md-6">
    										<div class="bg">
    						<h3>TEXTO - '.$log->data.'</h3>
    						<br>
    							<div class="row">
    								'.$log->texto.'
    							</div>
    							<br>
    					</div>
    				</div>
                    <div class="col-md-3">
    					<div class="bg">
    						<h3>ACOES RAPIDAS</h3>
    						<br>
                            '.form_button("editar", "EDITAR", array(
                            "id" => "button",
                            "value" => "true",
                            "type" => "reset",
                            "class" => "form-control btn btn-primary",
                                "onclick" => "window.location.href='/admin/changelog/edit/".$log->id."'")).'
                            <br><br>
                            '.form_button("deletar", "DELETAR", array(
                            "id" => "button",
                            "value" => "true",
                            "type" => "reset",
                            "class" => "form-control btn btn-primary",
                                "onclick" => "window.location.href='/admin/changelog/del/".$log->id."'")).'
                            <br><br>
                            '.form_button("menu", "IR /P PAINEL", array(
                            "id" => "button",
                            "value" => "true",
                            "type" => "reset",
                            "class" => "form-control btn btn-primary",
                                "onclick" => "window.location.href='/admin/changelog/'")).'
    					</div>
    				</div>';	
    			}
    			
			?>
			</div>
		</div>
	</div>