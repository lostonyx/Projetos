<?php echo $header; ?>
<!--<div class="container-fluid"><br />
  <br />
  <div class="row">
    <div class="col-sm-offset-4 col-sm-4">
      <div class="card">
        <div class="card-header">
          <h1 class="panel-title"><i class="fa fa-lock"></i> <?php echo $text_login; ?></h1>
        </div>
        <div class="panel-body">
          <?php if ($success) { ?>
          <div class="alert alert-success"><i class="fa fa-check-circle"></i> <?php echo $success; ?>
            <button type="button" class="close" data-dismiss="alert">&times;</button>
          </div>
          <?php } ?>
          <?php if ($error_warning) { ?>
          <div class="alert alert-danger"><i class="fa fa-exclamation-circle"></i> <?php echo $error_warning; ?>
            <button type="button" class="close" data-dismiss="alert">&times;</button>
          </div>
          <?php } ?>
          <form action="<?php echo $action; ?>" method="post" enctype="multipart/form-data">
            <div class="form-group">
              <label for="input-username"><?php echo $entry_username; ?></label>
              <div class="input-group"><span class="input-group-addon"><i class="fa fa-user"></i></span>
                <input type="text" name="username" value="<?php echo $username; ?>" placeholder="<?php echo $entry_username; ?>" id="input-username" class="form-control" />
              </div>
            </div>
            <div class="form-group">
              <label for="input-password"><?php echo $entry_password; ?></label>
              <div class="input-group"><span class="input-group-addon"><i class="fa fa-lock"></i></span>
                <input type="password" name="password" value="<?php echo $password; ?>" placeholder="<?php echo $entry_password; ?>" id="input-password" class="form-control" />
              </div>
              <?php if ($forgotten) { ?>
              <span class="help-block"><a href="<?php echo $forgotten; ?>"><?php echo $text_forgotten; ?></a></span>
              <?php } ?>
            </div>
            <div class="text-right">
              <button type="submit" class="btn btn-primary"><i class="fa fa-key"></i> <?php echo $button_login; ?></button>
            </div>
            <?php if ($redirect) { ?>
            <input type="hidden" name="redirect" value="<?php echo $redirect; ?>" />
            <?php } ?>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>-->
<!-- Login -->
<div class="lc-block toggled" id="l-login">

    <?php if ($success) { ?>
    <div class="alert alert-success"><i class="fa fa-check-circle"></i> <?php echo $success; ?>
        <button type="button" class="close" data-dismiss="alert">&times;</button>
    </div>
    <?php } ?>
    <?php if ($error_warning) { ?>
    <div class="alert alert-danger"><i class="fa fa-exclamation-circle"></i> <?php echo $error_warning; ?>
        <button type="button" class="close" data-dismiss="alert">&times;</button>
    </div>
    <?php } ?>

    <form action="<?=$action;?>" method="post" enctype="multipart/form-data">
        <div class="input-group m-b-20">
            <span class="input-group-addon"><i class="zmdi zmdi-account"></i></span>

            <div class="fg-line">
                <input type="text" name="username" value="<?php echo $username; ?>" placeholder="<?php echo $entry_username; ?>" class="form-control">
            </div>
        </div>

        <div class="input-group m-b-20">
            <span class="input-group-addon"><i class="zmdi zmdi-male"></i></span>

            <div class="fg-line">
                <input type="password" name="password" value="<?php echo $password; ?>" placeholder="<?php echo $entry_password; ?>" class="form-control">
            </div>
        </div>

        <div class="clearfix"></div>

        <button type="submit" class="btn btn-login btn-danger btn-float"><i class="zmdi zmdi-arrow-forward"></i></button>
    </form>

    <ul class="login-navigation">
        <?php if ($forgotten) { ?>
        <li data-block="#l-forget-password" class="bgm-orange"><?php echo $text_forgotten; ?></li>
        <?php } ?>
    </ul>
</div>

<!-- Forgot Password -->
<div class="lc-block" id="l-forget-password">
    <p class="text-left">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla eu risus. Curabitur commodo
        lorem fringilla enim feugiat commodo sed ac lacus.</p>

    <div class="input-group m-b-20">
        <span class="input-group-addon"><i class="zmdi zmdi-email"></i></span>

        <div class="fg-line">
            <input type="text" class="form-control" placeholder="Email Address">
        </div>
    </div>

    <a href="" class="btn btn-login btn-danger btn-float"><i class="zmdi zmdi-arrow-forward"></i></a>

    <ul class="login-navigation">
        <li data-block="#l-login" class="bgm-green">Login</li>
    </ul>
</div>

<!-- Older IE warning message -->
<!--[if lt IE 9]>
<div class="ie-warning">
    <h1 class="c-white">Warning!!</h1>

    <p>You are using an outdated version of Internet Explorer, please upgrade <br/>to any of the following web browsers
        to access this website.</p>

    <div class="iew-container">
        <ul class="iew-download">
            <li>
                <a href="http://www.google.com/chrome/">
                    <img src="dashboard/view/image/browsers/chrome.png" alt="">

                    <div>Chrome</div>
                </a>
            </li>
            <li>
                <a href="https://www.mozilla.org/en-US/firefox/new/">
                    <img src="dashboard/view/image/browsers/firefox.png" alt="">

                    <div>Firefox</div>
                </a>
            </li>
            <li>
                <a href="http://www.opera.com">
                    <img src="dashboard/view/image/browsers/opera.png" alt="">

                    <div>Opera</div>
                </a>
            </li>
            <li>
                <a href="https://www.apple.com/safari/">
                    <img src="dashboard/view/image/browsers/safari.png" alt="">

                    <div>Safari</div>
                </a>
            </li>
            <li>
                <a href="http://windows.microsoft.com/en-us/internet-explorer/download-ie">
                    <img src="dashboard/view/image/browsers/ie.png" alt="">

                    <div>IE (New)</div>
                </a>
            </li>
        </ul>
    </div>
    <p>Sorry for the inconvenience!</p>
</div>
<![endif]-->

<?php echo $footer; ?>