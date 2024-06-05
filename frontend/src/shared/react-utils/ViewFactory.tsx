import React, { FC, PropsWithChildren } from "react";

interface ViewModelComponent<ViewProps, ViewModel> {
  (props: ViewProps): ViewModel
}

export const ViewFactory = <ViewProps extends PropsWithChildren, ViewModel>(
	viewModel: ViewModelComponent<ViewProps, ViewModel>,
	ViewComponent: FC<ViewModel & ViewProps>
  ) => {
    	return function View(props: ViewProps) {
		return (
          		<ViewComponent {...props} {...viewModel(props)} />
      		)
    	}
  }
