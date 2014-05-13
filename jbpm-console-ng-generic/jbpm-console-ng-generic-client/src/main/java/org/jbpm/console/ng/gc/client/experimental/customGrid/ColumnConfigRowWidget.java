package org.jbpm.console.ng.gc.client.experimental.customGrid;

import com.github.gwtbootstrap.client.ui.Icon;
import com.github.gwtbootstrap.client.ui.constants.IconType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.*;

public class ColumnConfigRowWidget extends Composite {

	interface ColumnConfigRowWidgetUIBinder
			extends UiBinder<Widget, ColumnConfigRowWidget> {
	}

	private static ColumnConfigRowWidgetUIBinder uiBinder = GWT.create( ColumnConfigRowWidgetUIBinder.class );

	@UiField
	HTMLPanel rowPanel;

	final private Icon shiftRightIcon = new Icon( IconType.ARROW_DOWN );
	final private Icon shiftLeftIcon = new Icon( IconType.ARROW_UP );

	public ColumnConfigRowWidget( Boolean isVisible,
								  final ColumnVisibilityChangedCallback columnVisibilityChangedCallback,
								  String columnLabel,
								  final RightColumnShiftCallback rightShiftCallback,
								  final LeftColumnShiftCallback leftShiftCallback ) {
		initWidget( uiBinder.createAndBindUi( this ) );

		final CheckBox checkBox = new com.google.gwt.user.client.ui.CheckBox();
		checkBox.setValue( isVisible );
		checkBox.addClickHandler( new ClickHandler() {
			@Override
			public void onClick( ClickEvent event ) {
				boolean isVisible = checkBox.getValue();
				shiftLeftIcon.setVisible( isVisible );
				shiftRightIcon.setVisible( isVisible );
				columnVisibilityChangedCallback.columnVisibilityChanged( checkBox.getValue() );
			}
		} );


		rowPanel.add( checkBox, "columnVisibleId" );
		rowPanel.add( new Label( columnLabel ), "columnLabelId" );

		if ( rightShiftCallback != null ) {
			shiftRightIcon.getElement().getStyle().setCursor( Style.Cursor.POINTER );
			shiftRightIcon.sinkEvents( Event.ONCLICK );
			shiftRightIcon.addHandler( new ClickHandler() {
				@Override
				public void onClick( ClickEvent event ) {
					rightShiftCallback.columnShiftedRight();
				}
			}, ClickEvent.getType() );

			rowPanel.add( shiftRightIcon, "columnShiftRightIcon" );
		}
		shiftRightIcon.setVisible( isVisible );

		if ( leftShiftCallback != null ) {
			shiftLeftIcon.getElement().getStyle().setCursor( Style.Cursor.POINTER );
			shiftLeftIcon.sinkEvents( Event.ONCLICK );
			shiftLeftIcon.addHandler( new ClickHandler() {
				@Override
				public void onClick( ClickEvent event ) {
					leftShiftCallback.columnShiftedLeft();
				}
			}, ClickEvent.getType() );

			rowPanel.add( shiftLeftIcon, "columnShiftLeftIcon" );
		}
		shiftLeftIcon.setVisible( isVisible );
	}
}
