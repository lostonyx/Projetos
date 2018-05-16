<?php

/*
 * This file is part of the Monolog package.
 *
 * (c) Jordi Boggiano <j.boggiano@seld.be>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

namespace Monolog\Handler;

use Monolog\Logger;

/**
 * Base Handler class providing basic level/bubble support
 *
 * @author Jordi Boggiano <j.boggiano@seld.be>
 */
abstract class AbstractHandler extends Handler
{

    protected $level = Logger::DEBUG;
    protected $bubble = true;

    /**
     * @param int $level The minimum logging level at which this handler will be triggered
     * @param Boolean $bubble Whether the messages that are handled can bubble up the stack or not
     */
    public function __construct($level = Logger::DEBUG, $bubble = true)
    {
        $this->setLevel($level);
        $this->bubble = $bubble;
    }

    /**
     * {@inheritdoc}
     */
    public function isHandling(array $record)
    {
        return $record['level'] >= $this->level;
    }

    /**
     * Gets minimum logging level at which this handler will be triggered.
     *
     * @return int
     */
    public function getLevel()
    {
        return $this->level;
    }

    /**
     * Sets minimum logging level at which this handler will be triggered.
     *
     * @param  int|string $level Level or level name
     * @return self
     */
    public function setLevel($level)
    {
        $this->level = Logger::toMonologLevel($level);

        return $this;
    }

    /**
     * Gets the bubbling behavior.
     *
     * @return Boolean true means that this handler allows bubbling.
     *                 false means that bubbling is not permitted.
     */
    public function getBubble()
    {
        return $this->bubble;
    }

    /**
     * Sets the bubbling behavior.
     *
     * @param  Boolean $bubble true means that this handler allows bubbling.
     *                         false means that bubbling is not permitted.
     * @return self
     */
    public function setBubble(bool $bubble)
    {
        $this->bubble = $bubble;

        return $this;
    }

}
