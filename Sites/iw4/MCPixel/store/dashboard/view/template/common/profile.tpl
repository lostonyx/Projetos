<div class="profile-menu">
    <a href="">
        <?php if ($image) { ?>
        <img src="<?php echo $image; ?>" alt="<?php echo $firstname; ?> <?php echo $lastname; ?>"
             title="<?php echo $username; ?>" class="img-circle"/>
        <?php } else { ?>
        <img src="dashboard/view/image/profile.jpg" alt="<?php echo $firstname; ?> <?php echo $lastname; ?>"
             title="<?php echo $username; ?>" class="img-circle"/>
        <?php } ?>

        <div class="profile-info">
            <?php echo $firstname; ?> <?php echo $lastname; ?><br><small><?php echo $user_group; ?></small>
            <i class="zmdi zmdi-arrow-drop-down"></i>
        </div>
    </a>

    <ul class="main-menu">
        <li>
            <a href="dashboard/common/logout"><i class="zmdi zmdi-time-restore"></i> Logout</a>
        </li>
    </ul>
</div>

