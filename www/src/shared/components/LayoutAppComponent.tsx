import Grid2 from "@mui/material/Unstable_Grid2";
import {PropsWithChildren, ReactNode} from "react";

interface LayoutAppComponentProps extends PropsWithChildren{
  header: ReactNode;
  menu: ReactNode;
  footer?: ReactNode;
}

export const LayoutAppComponent = ({ children, header, footer, menu }: LayoutAppComponentProps) => (
  <Grid2 container>
    <Grid2>
      {header}
    </Grid2>
    <Grid2>
      {children}
    </Grid2>
    {
      !!footer &&
        <Grid2>
          {footer}
        </Grid2>
    }
  </Grid2>
)
