import Grid2 from "@mui/material/Unstable_Grid2";
import {PropsWithChildren, ReactNode} from "react";
import {Box} from "@mui/material";

interface LayoutAppComponentProps extends PropsWithChildren{
  header: ReactNode;
  footer?: ReactNode;
}

export const LayoutAppComponent = ({ children, header, footer }: LayoutAppComponentProps) => (
  <Box>
    {header}
    <Grid2>
      {children}
    </Grid2>
    {
      !!footer &&
        <Grid2>
          {footer}
        </Grid2>
    }
  </Box>
)
