/*
 * PCGenAction.java
 * Copyright 2008 Connor Petty <cpmeister@users.sourceforge.net>
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 * 
 * Created on Aug 14, 2008, 3:51:27 PM
 */
package pcgen.gui.tools;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.StringTokenizer;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import pcgen.gui.tools.ResourceManager.Icons;
import pcgen.util.PropertyFactory;

public class PCGenAction extends AbstractAction
{

    public PCGenAction(String prop)
    {
        this(prop, null, null, null);
    }

    public PCGenAction(String prop, Icons icon)
    {
        this(prop, null, null, icon);
    }

    public PCGenAction(String prop, String command)
    {
        this(prop, command, null, null);
    }

    public PCGenAction(String prop, String command, Icons icon)
    {
        this(prop, command, null, icon);
    }

    public PCGenAction(String prop, String command, String accelerator)
    {
        this(prop, command, accelerator, null);
    }

    public PCGenAction(String prop, String command, String accelerator,
                        Icons icon)
    {
        putValue(NAME,
                 PropertyFactory.getString("in_" + prop));
        putValue(MNEMONIC_KEY,
                 PropertyFactory.getMnemonic("in_mn_" + prop));
        putValue(SHORT_DESCRIPTION,
                 PropertyFactory.getString("in_" + prop + "Tip"));

        if (command != null)
        {
            putValue(ACTION_COMMAND_KEY, command);
        }
        if (accelerator != null)
        {
            // accelerator has three possible forms:
            // 1) shortcut +
            // 2) shortcut-alt +
            // 3) F1
            // (error checking is for the weak!)
            int iShortCut = KeyEvent.CTRL_MASK;
            int menuShortcutKeyMask = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
            StringTokenizer aTok = new StringTokenizer(accelerator);

            // get the first argument
            String aString = aTok.nextToken();

            if (aString.equalsIgnoreCase("shortcut"))
            {
                iShortCut = menuShortcutKeyMask;
            }
            else if (aString.equalsIgnoreCase("alt"))
            {
                if (System.getProperty("mrj.version") != null)
                {
                    iShortCut = menuShortcutKeyMask | KeyEvent.ALT_MASK;
                }
                else
                {
                    iShortCut = KeyEvent.ALT_MASK;
                }
            }
            else if (aString.equalsIgnoreCase("shift-shortcut"))
            {
                iShortCut = menuShortcutKeyMask | KeyEvent.SHIFT_MASK;
            }

            if (aTok.hasMoreTokens())
            {
                // get the second argument
                aString = aTok.nextToken();
            }

            KeyStroke aKey = KeyStroke.getKeyStroke(aString);

            if (aKey != null)
            {
                int iKeyCode = aKey.getKeyCode();
                putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(iKeyCode,
                                                                 iShortCut));
            }
        }
        if (icon != null)
        {
            putValue(SMALL_ICON, ResourceManager.getImageIcon(icon));
        }
    }

    public void actionPerformed(ActionEvent e)
    {

    }

}
