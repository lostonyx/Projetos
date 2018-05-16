<?php if (count($currencies) > 1) { ?>
<li class="dropdown">
    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><span class="label label-nav label-custom-icon"><i class="fa fa-shopping-cart"></i></span> <?=$code?> <span class="caret"></span></a>
    <ul class="dropdown-menu" role="menu">
        <?php foreach ($currencies as $currency) { ?>
        <li><a href="common/currency/currency?code=<?=$currency['code']?>&redirect=<?=$href?>"><i class="fa <?=($code === $currency['code'])?"fa-check-circle":"fa-circle-o"?>"></i> <?=$currency['code']?></a></li>	
        <?php } ?>				
    </ul>
</li>
<?php } ?>
