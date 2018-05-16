	<nav
		class="navbar navbar-light
bg-faded navbar-static-top dash-navbar-top nb-visible">
		<button class="nb-btn-toggle">
			<span class="fa fa-bars"></span>
		</button>
	</nav>
	<div class="dash-navbar-left nb-visible">
		<a class="navbar-brand" href="#">Painel</a>
		<p class="nb-nav-title">Olá, <?php echo $this->my_auth->get('user', 'name');
  ?></p>
		<ul class="nb-nav">
			<li><a href="/client"> <span class="ion-ios-speedometer-outline"></span>
					<span class="nb-link-text">Dashboard</span>
			</a></li>
			<li><a href="/client/plugins"> <span class="ion-ios-filing-outline"></span>
					<span class="nb-link-text">Meus plugins</span>
			</a></li>
			<li><a href="/client/changelog"> <span
					class="ion-ios-calendar-outline"></span> <span class="nb-link-text">Changelog</span>
			</a></li>
			<li><a href="/client/logs"> <span class="ion-ios-list-outline"></span>
					<span class="nb-link-text">Logs</span>
			</a></li>
			<li><a href="/client/transactions"> <span class="ion-shuffle"></span>
					<span class="nb-link-text">Transações</span>
			</a></li>
			<li><a href="/client/docs"> <span class="ion-ios-medkit-outline"></span>
					<span class="nb-link-text">Documentação</span>
			</a></li>
			<li><a href="/client/logout"> <span class="ion-log-out"></span> <span
					class="nb-link-text">Sair</span>
			</a></li>
		</ul>
	</div>
	<div class="content-wrap
nb-visible" data-effect="nb-push">
		<div class="container-fluid">
			<div class="page-title">
				Documentação <span>Está com dúvidas? Hora de tira-las</span>
			</div>
			<br>
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-body docs">
							<h4 class="no-margin">1) COMO BAIXAR PLUGIN</h4>
							<hr>
							<p>
								Para baixar o plugin vá em: <br /> <a
									href="https://tintadev.com/client/plugins"><img
									src="https://image.prntscr.com/image/mJki85dFQb6J3OHhMR9CnA.png" /></a><br /></br>
								E depois <b>clique</b> na opção de download do plugin que deseja
								baixar: <img
									src="https://image.prntscr.com/image/vltrfVWFRPGV-113K46YQw.png" />
							</p>
							<br>
							<h4 class="no-margin">2) COMO EDITAR A LICENÇA</h4>
							<hr>
							<p>
								Ainda na aba do "Meus Plugins" <b>clique</b> na opção ao lado do
								download: <img
									src="https://image.prntscr.com/image/ORl6yY0fQfqW__em7WKotA.png" /><br />
								Ira abrir um menu, nesse menu coloque o ip numérico do servidor
								<b>sem porta</b>, caso não possua hospedagem, coloque <b><a
									href="http://www.meuip.com.br/" target="_blank">seu ip</a></b>
								e salve.
							</p>
							<br>
							<h4 class="no-margin">3) COMO USAR A LICENÇA</h4>
							<hr>
							<p>
								Para usar a licença <b>copie</b> a que deseja usar do respectivo
								plugin.<br /> <img
									src="https://image.prntscr.com/image/cv9KR6THS22cuxpjy44kfA.png" />
							</p>
							<br />
							<p>
								Agora vá na pasta do seu servidor, vá até pasta plugins e depois
								até pasta do plugin que deseja ativar.<br /> O mesmo terá um
								arquivo chamado <b>licenca.yml</b>.<br /> <img
									src="https://image.prntscr.com/image/YB_Vm7g9SUmnQFmDJssydg.png" />
							</p>
							<br />
							<p>
								Abra ele e <b>coloque</b> sua licença no lugar de
								"0000-0000-0000-0000"
							</p>
							<br />
							<p>
								<b>Antes:</b></b> <img
									src="https://image.prntscr.com/image/y9mtWbpXTHOgYIjICOB1Ug.png" /><br />
								<b>Depois:</b> <img
									src="https://image.prntscr.com/image/ZcDa8jwFSvacnLoZZMQEUg.png" /><br />
								<br />
							
							
							<h4 class="no-margin">4) VISUALIZAR AS LOGS</h4>
							<hr>
							<p>
								Caso o plugin <b>não ative</b>, verifique a log e veja qual foi
								o problema, em alguns casos pode ser erro na licença ou até
								mesmo um erro no mysql.
							</p>
							<br />
							<p>
								Caso o erro foi de mysql simplesmente entre na <b>config.yml</b>,
								ative o mysql e configure corretamente o mesmo.
							</p>
							<br />
							<p>
								Caso for um problema de licença, vá para:<br /> <a
									href="https://tintadev.com/client/logs"><img
									src="https://image.prntscr.com/image/S_C5Vwe-TnG8LEosOI1rvw.png" /></a>
							</p>
							<br />
							<p>
								Na aba logs será possivel ver o registro do uso de suas
								licenças, assim poderá ver os últimos ips utilizados, pois <b>algumas
									hospedagens camuflam o ip real</b> então você pega o ip da log
								e altera de acordo com tutorial 2.
							</p>
							<br />
							<p>
								<b>Lembre-se pegar somente ip e a porta não</b>
							</p>
							<br />
							<p>
								<img
									src="https://image.prntscr.com/image/2Vce0gWJT3yRsW0jRInuHw.png" />
							</p>
							<br />
							<p>
								<b>Caso achar algo suspeito na sua log, crie um ticket
									informando</b>
							</p>
							</br>
							<h4 class="no-margin">5) SISTEMA DE AFILIADOS</h4>
							<hr>
							<p>
								Vá até a pagina de afiliados<br />
								<a href="https://tintadev.com/client/affiliate"><img
									src="https://image.prntscr.com/image/JB9pgQqMT2Gt_qpoCjF_gw.png" /></a><br />
								<br />Para começar usar primeiramente <strong>pegue</strong> o
								seu link de afiliado<br />
								<img
									src="https://image.prntscr.com/image/nY3u3IhET9CgxJcABjlvsQ.png" /><br />
								<br />E <b>divulgue</b> este link.
							</p>
							<p>
								Caso alguém que você indicar comprar pelo seu link ira registrar
								no seu painel de afiliados.<br />
								<br />Clientes que recomendarem outras pessoas terão <b>benefícios</b>,
								depende o <b>quanto você indicar</b>, alguns casos podem receber
								<b>plugins de graça</b>, outros receber <b>cupons de desconto</b>.
							</p>
							<br />
							<br />
							<h4 class="no-margin">6) CLIENTE PLUS</h4>
							<hr>
							<h5>
								Sistema em desenvolvimento.
								</h2>
						
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>