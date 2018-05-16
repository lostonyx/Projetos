
	<header class="intro">
		<div class="mid">
			<div class="container">
				<h3>Área do Cliente</h3>
			</div>
		</div>
	</header>
	<div class="content-wrapper">
		<div class="blank">
			<h4 class="no-margin">Entrar</h4>
		</div>
		<div class="bg">
			<div class="row">
				<div class="col-md-4"></div>
				<div class="col-md-4">
					<div class="form-login">
					<?php
                        echo form_open("client/autenticar");
             
                        echo form_label("Usuario", "Usuario");
                            echo form_input(array(
                                    "name" => "username",
                                    "id" => "username",
                                    "class" => "form-control",
                                    "naxlenth" => "255"
                                ));
                        echo form_label("Senha", "senha");
                            echo form_password(array(
                                    "name" => "password",
                                    "id" => "password",
                                    "class" => "form-control",
                                    "naxlenth" => "255"
                                )); 
                        echo form_button(array(
                            "class" => "waves waves-effect waves-light",
                            "content" => "Entrar",
                            "type" => "submit"
                        )); 
                    ?>
					<div class="alert alert-success" style="display: none;"></div>
					<div class="alert alert-danger" style="display: none;"></div>
					<h1><?php
			             echo show_message();
                         echo form_close();
                    ?></h1>
                    <?php echo anchor('client/criar', 'Criar Uma Conta', array('class' => 'waves waves-effect waves-light')); ?>
					</div>
				</div>
			</div>
		</div>
	</div>