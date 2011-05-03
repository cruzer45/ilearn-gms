/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ilearn.kernel;

import ilearn.ILearnApp;

/**
 *
 * @author mrogers
 */
public class WindowHandler extends java.awt.event.WindowAdapter
{

    public WindowHandler()
    {
        super();
    }

    @Override
    public void windowClosing(java.awt.event.WindowEvent evt)
    {
        System.out.println("Window closing captured");
        ILearnApp.getInstance().exit();
    }
}
