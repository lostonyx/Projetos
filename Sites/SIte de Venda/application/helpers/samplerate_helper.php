<?php
function samplerate($bitrate, $channel, $type = 1) {    
    if ($type == 1){
        $listsHtz = array('32' => array('1' => '22050',
                                        '2' => '16000'),
                          '48' => array('1' => '22050',
                                        '2' => '16000'),
                          '64' => array('1' => '44100',
                                        '2' => '22050'),
                          '96' => array('1' => '44100',
                                        '2' => '44100'),
                         '128' => array('1' => '44100',
                                        '2' => '44100'));
    }
    else {
        $listsHtz = array('32' => array('1' => '44100',
                                        '2' => '22050'),
                          '48' => array('1' => '44100',
                                        '2' => '22050'),
                          '64' => array('1' => '44100',
                                        '2' => '22050'),
                          '96' => array('1' => '44100',
                                        '2' => '22050'),
                         '128' => array('1' => '44100',
                                        '2' => '22050'));
    }
    
    if (isset($listsHtz[$bitrate][$channel])) {
        return $listsHtz[$bitrate][$channel];
    } else {
        die("ERRO!");
    }
}

function recChannel($type, $bitrate){
    if ($type == 1){
        if ($bitrate <= 64){
            return 'Mono';
        }
        else {
            return 'Stereo';
        }
    }
    else if ($type == 2){
        if ($bitrate == 32){
            return 'Stereo V2';
        }
        else {
            return 'Stereo';
        }
    }
}
?>
