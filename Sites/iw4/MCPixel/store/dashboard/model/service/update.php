<?php

use \VisualAppeal\AutoUpdate;

class ModelServiceUpdate extends Model {

    public function run() {
        $update = new AutoUpdate(DIR_ROOT . '/system/update/temp', DIR_ROOT, 60);
        $update->setCurrentVersion(VERSION);
        $update->setUpdateUrl('http://api.vleep.com.br/updater/iw4');
        $update->addLogHandler(new Monolog\Handler\StreamHandler(DIR_ROOT . '/system/update/update.log'));
        $update->setCache(new Desarrolla2\Cache\Adapter\File(DIR_ROOT . '/system/update/cache'), 3600);

        if ($update->checkUpdate() === false) {
            $this->log->write('Could not check for updates! See log file for details.');
        }

        if ($update->newVersionAvailable()) {
            $this->log->write('[Info] New Version: '.$update->getLatestVersion());
            $result = $update->update();
            if ($result !== true) {
                $str = 'Update failed: ' . $result . '!';
                $this->log->write($str);
            } else {
				$install = DIR_SYSTEM . '/update/install/' . $update->getLatestVersion() . '.php';
                if (is_readable($install)) {
                    require($install);
                }
                $this->log->write('[Info] Update successful!');
            }
        }
    }

}