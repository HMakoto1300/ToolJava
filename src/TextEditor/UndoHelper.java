package TextEditor;

import java.awt.Event;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;
import javax.swing.text.AbstractDocument.DefaultDocumentEvent;
import javax.swing.undo.UndoManager;

/**
 * �w�肳�ꂽ�e�L�X�g�R���|�[�l���g��Undo/Redo�@�\�����܂��B
 * 
 * @author a-san
 */
public class UndoHelper {
    public static final String ACTION_KEY_UNDO = "undo";
    public static final String ACTION_KEY_REDO = "redo";
    UndoManager undoManager = new UndoManager();
    
    /** �w�肳�ꂽ�e�L�X�g�R���|�[�l���g��Undo/Redo�@�\�����܂��B�@*/
    public UndoHelper(JTextComponent textComponent) {
        ActionMap amap = textComponent.getActionMap();
        InputMap imap = textComponent.getInputMap();
        if (amap.get(ACTION_KEY_UNDO) == null) {
            UndoAction undoAction = new UndoAction();
            amap.put(ACTION_KEY_UNDO, undoAction);
            imap.put((KeyStroke) undoAction.getValue(Action.ACCELERATOR_KEY), ACTION_KEY_UNDO);
        }
        if (amap.get(ACTION_KEY_REDO) == null) {
            RedoAction redoAction = new RedoAction();
            amap.put(ACTION_KEY_REDO, redoAction);
            imap.put((KeyStroke) redoAction.getValue(Action.ACCELERATOR_KEY), ACTION_KEY_REDO);
        }
        // ���X�i��o�^
        textComponent.getDocument().addDocumentListener(new DocListener());
    }
    public UndoManager getUndoManager() { return undoManager; }
    /**
     * ���ɖ߂�
     */
    class UndoAction extends AbstractAction {
        UndoAction() {
            super("���ɖ߂�(U)");
            putValue(MNEMONIC_KEY, new Integer('U'));
            putValue(SHORT_DESCRIPTION, "���ɖ߂�");
            putValue(LONG_DESCRIPTION, "���ɖ߂�");
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke('Z', Event.CTRL_MASK));
//            putValue(SMALL_ICON, SwingUtil.getImageIcon("/resources/EditUndo.png"));
        }
        public void actionPerformed(ActionEvent e) {
            if (undoManager.canUndo()) {
                undoManager.undo();
            }
        }
    }

    /**
     * ��蒼��
     */
    class RedoAction extends AbstractAction {
        RedoAction() {
            super("��蒼��(R)");
            putValue(MNEMONIC_KEY, new Integer('R'));
            putValue(SHORT_DESCRIPTION, "��蒼��");
            putValue(LONG_DESCRIPTION, "��蒼��");
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke('Y', Event.CTRL_MASK));
//            putValue(SMALL_ICON, SwingUtil.getImageIcon("/resources/EditRedo.png"));
        }
        public void actionPerformed(ActionEvent e) {
            if (undoManager.canRedo()) {
                undoManager.redo();
            }
        }
    }
    /** �h�L�������g���ύX���ꂽ�Ƃ��̃��X�i�[. */
    private class DocListener implements DocumentListener {
        public void insertUpdate(DocumentEvent e) {
            if (e instanceof DefaultDocumentEvent) {
                DefaultDocumentEvent de = (DefaultDocumentEvent) e;
                undoManager.addEdit(de);
            }
        }
        public void removeUpdate(DocumentEvent e) {
            if (e instanceof DefaultDocumentEvent) {
                DefaultDocumentEvent de = (DefaultDocumentEvent) e;
                undoManager.addEdit(de);
            }
        }
        public void changedUpdate(DocumentEvent e) {
            // �������ς�����Ƃ��́A�������Ȃ��Ă悢�B
        }
    }   
}