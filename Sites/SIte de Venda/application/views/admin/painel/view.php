	<div class="content-wrap
nb-visible" data-effect="nb-push">
		<div class="container-fluid">
			<div class="page-title">
				Dashboard <span>Monitore seus plugins</span>
			</div>
			<br>
			<div class="row">
				<div class="col-md-4">
					<div class="card">
						<div class="card-body">
							<h1 class="no-margin noselect">
								<i class="ion-shuffle"></i> <small>Transações</small> <span
									class="float-right"><?php echo sizeof($trans); ?></span>
							</h1>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="card">
						<div class="card-body">
							<h1 class="no-margin noselect">
								<i class="ion-code-download"></i> <small>Plugins</small> <span
									class="float-right"><?php echo sizeof($plugins); ?></span>
							</h1>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="card">
						<div class="card-body">
							<h1 class="no-margin noselect">
								<i class="ion-code-working"></i> <small>Licenças</small> <span
									class="float-right"><?php echo sizeof($lis); ?></span>
							</h1>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<br>
					<br>
					<div class="card">
						<div class="card-body">
							<h5 class="noselect">
								<i class="ion-alert-circled"></i> AVISOS
							</h5>
							<div class="alerts">
							<?php 
							if(sizeof($avisos) > 0){
							    foreach ($avisos as $aviso){
							        echo '<div>
									<div class="row">
										<div class="col-3">'.$aviso->data.'</div>
										<div class="col-9">'.$aviso->texto.'</div>
									</div>
								</div>';
							    }
							}else{
							  echo '<div>
									<div class="row">
										<div class="col-9">Sem Aviso no Momento!</div>
									</div>
								</div>';  
							}
							
							?>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-3">
					<br>
					<br>
					<div class="card">
						<div class="card-body">
							<h5 class="noselect">
								<i class="ion-person"></i> TROCAR SENHA
							</h5>
							<br>
							<form action="<?php echo base_url();?>/client/change" method="post">
								<h4><?php echo show_message();?> </h4><br><br>
								<?php 
								
								if(strlen(show_message()) > 0){
    							    /*echo '<div class="alert alert-success">';
    							    echo "<span>Alguma coisa deu erro!</span>";
                                    echo '</div>';*/
    							}
    							else{
    							    echo show_message();
    							}?>
								<div class="form-group">
									<label>Senha atual</label> <input type="password"
										class="form-control" name="now">
								</div>
								<div class="form-group">
									<label>Nova senha</label> <input type="password"
										class="form-control" name="new">
								</div>
								<div class="form-group">
									<label>Repita a senha</label> <input type="password"
										class="form-control" name="repeat">
								</div>
								<button class="btn btn-primary" style="width: 100%;">Alterar</button>
							</form>
						</div>
					</div>
				</div>
				<?php 
				/*
				<div class="col-md-3">
					<br>
					<br>
					<div class="card">
						<div class="card-body">
							<h5 class="noselect">
								<i class="ion-trophy"></i> TOP COMPRADORES
							</h5>
							<br>
							<table class="table">
								<thead>
									<tr>
										<th scope="col">Usuário</th>
										<th scope="col">Plugins</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td><span style='color: #fbc02d; font-weight: 800'>BigHeroZ_</span></td>
										<td>37</td>
									</tr>
									<tr>
										<td><span style='color: #9e9e9e; font-weight: 600'>Lemu_</span></td>
										<td>29</td>
									</tr>
									<tr>
										<td><span style='color: #795548; font-weight: 600'>Fino_</span></td>
										<td>28</td>
									</tr>
									<tr>
										<td>JoaoOtavioS</td>
										<td>26</td>
									</tr>
									<tr>
										<td>noBrac</td>
										<td>20</td>
									</tr>
									<tr>
										<td>yLeonardo</td>
										<td>19</td>
									</tr>
									<tr>
										<td>zPedroo_</td>
										<td>19</td>
									</tr>
									<tr>
										<td>KlaynT</td>
										<td>19</td>
									</tr>
									<tr>
										<td>FuturyNetwork</td>
										<td>18</td>
									</tr>
									<tr>
										<td>grock77</td>
										<td>18</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				 */
				?>
			</div>
		</div>
	</div>

