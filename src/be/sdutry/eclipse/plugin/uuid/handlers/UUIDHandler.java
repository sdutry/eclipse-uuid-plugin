package be.sdutry.eclipse.plugin.uuid.handlers;

import java.util.UUID;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.texteditor.ITextEditor; 

public class UUIDHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// Generate a UUID
        String uuid = UUID.randomUUID().toString();

        // Get the active editor
        IEditorPart editorPart = org.eclipse.ui.handlers.HandlerUtil.getActiveEditor(event);

        if (editorPart instanceof ITextEditor textEditor) {
            // Get the document being edited
            IDocument document = textEditor.getDocumentProvider().getDocument(textEditor.getEditorInput());

            // Get the current cursor position
            ITextSelection selection = (ITextSelection) textEditor.getSelectionProvider().getSelection();
            int offset = selection.getOffset();

            try {
                // Insert the UUID at the cursor position
                document.replace(offset, 0, uuid);
            } catch (Exception e) {
                throw new ExecutionException("Failed to insert UUID", e);
            }
        }

        return null;
	}
}
