
<header class="intro">
	<div class="mid">
		<div class="container"></div>
	</div>
</header>
<div class="content-wrapper">
	<div class="blank">
		<h4 class="no-margin">Loja - Free</h4>
	</div>
	<div class="bg">
		<div class="row">
			<div class="col-md-9">
				<div class='row'>
				<?php
				if(sizeof($plugins) > 0){
				    foreach ($plugins as $plugin) {
				        echo '<div class="col-md-3">
                        <div class="plugin-1">
                        	<img class="img-fluid" src="' . asset_url() . 'images/utils/plugin.png">
                        	<div class="title">' . $plugin->nome . '</div>
                            ' . form_button('baixar', 'BAIXAR', array(
                                'id' => 'button',
                                'value' => 'true',
                                'type' => 'reset',
                                'class' => 'waves',
                                "onclick" => "window.location.href='/loja/baixar/" . $plugin->id . "'"
                            )) . '
                		</div>
                	</div>';
				    }
				}
				else{
				    echo '<h1 align="center">NENHUM DISPONIVEL!</h1>'; 
				}
                ?>
				</div>
				<nav>
					<?php echo $pagination; ?>
				</nav>
			</div>
		</div>
	</div>
</div>