	<header class="intro">

	</header>
	<div class="content-wrapper">
		<div class="blank">
			<div class="row">
				<div class="col-md-8">
				<h5 class="col-12">Cadastre-se</h5>
				<?php
                        echo form_open("client/registrar");
                        echo form_label("Seu nome (Como devo chamar?):", "name");
                        echo form_input(array(
                            "name" => "name",
                            "id" => "name",
                            "class" => "form-control",
                            "naxlenth" => "255"
                        ));
                        echo form_label("Usuário:", "username");
                            echo form_input(array(
                                    "name" => "username",
                                    "id" => "username",
                                    "class" => "form-control",
                                    "naxlenth" => "255"
                                ));
                            echo form_label("Senha:", "password");
                            echo form_password(array(
                                "name" => "password",
                                "type" => "password",
                                "id" => "password",
                                "class" => "form-control",
                                "naxlenth" => "255"
                            )); 
                        echo form_label("Repita a senha:", "repeat");
                            echo form_password(array(
                                "name" => "repeat",
                                "type" => "password",
                                "id" => "repeat",
                                "class" => "form-control",
                                "naxlenth" => "255"
                            )); 
                        echo form_button(array(
                            "class" => "btn btn-primary waves waves-effect waves-light",
                            "content" => "CADASTRAR",
                            "type" => "submit"
                        )); 
                    ?>
                    <h1><?php
			             echo show_message();
                         echo form_close();
                    ?></h1>
				</div>
			</div>
		</div>
	</div>