package org.waffel.wordpress.demo.webstart.plugin;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

public class View extends ViewPart {
  /**
   * The content provider class is responsible for providing objects to the view. It can wrap existing objects in
   * adapters or simply return objects as-is. These objects may be sensitive to the current input of the view, or ignore
   * it and always show the same content (like Task List, for example).
   */
  class ViewContentProvider implements IStructuredContentProvider {
    public void dispose() {}

    public Object[] getElements(final Object parent) {
      if (parent instanceof Object[]) {
        return (Object[]) parent;
      }
      return new Object[0];
    }

    public void inputChanged(final Viewer v, final Object oldInput, final Object newInput) {}
  }

  class ViewLabelProvider extends LabelProvider implements ITableLabelProvider {
    public Image getColumnImage(final Object obj, final int index) {
      return getImage(obj);
    }

    public String getColumnText(final Object obj, final int index) {
      return getText(obj);
    }

    @Override
    public Image getImage(final Object obj) {
      return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
    }
  }

  public static final String ID = "org.waffel.wordpress.demo.webstart.plugin.view";

  private TableViewer viewer;

  /**
   * This is a callback that will allow us to create the viewer and initialize it.
   */
  @Override
  public void createPartControl(final Composite parent) {
    viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
    viewer.setContentProvider(new ViewContentProvider());
    viewer.setLabelProvider(new ViewLabelProvider());
    // Provide the input to the ContentProvider
    viewer.setInput(new String[] { "One", "Two", "Three" });
  }

  /**
   * Passing the focus request to the viewer's control.
   */
  @Override
  public void setFocus() {
    viewer.getControl().setFocus();
  }
}