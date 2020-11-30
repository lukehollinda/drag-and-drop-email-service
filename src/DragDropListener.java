import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.File;
import java.util.List;

public class DragDropListener implements DropTargetListener{

	@Override
	public void drop(DropTargetDropEvent event)
	{
		//Accept copy drops
		event.acceptDrop(DnDConstants.ACTION_COPY);
		
		//Get the transfer which provides dropped item data
		Transferable transferable = event.getTransferable();
		
		//Get data formats of the dropped item
		DataFlavor[] flavors = transferable.getTransferDataFlavors();
		
		for(DataFlavor flavor : flavors)
		{
			try
			{
				//If the drop items are files
				if(flavor.isFlavorJavaFileListType())
				{
				
					@SuppressWarnings("unchecked")
					List<File> files = (List<File>) transferable.getTransferData(flavor);
					
					EmailService.sendFormattedEmail(files);
					
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		//Inform that the drop is complete
		event.dropComplete(true);
	}

	@Override
	public void dragEnter(DropTargetDragEvent dtde) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dragOver(DropTargetDragEvent dtde) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dropActionChanged(DropTargetDragEvent dtde) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dragExit(DropTargetEvent dte) {
		// TODO Auto-generated method stub
		
	}
}
