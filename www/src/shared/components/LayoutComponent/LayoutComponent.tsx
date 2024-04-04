import {PropsWithChildren} from "react";
import {Paper, Box} from "@mui/material";


export interface LayoutComponentProps extends PropsWithChildren {

}

export const LayoutComponent = ({children}: LayoutComponentProps) => (
    <Box
      display={"flex"}
      alignItems={"center"}
      alignSelf={"center"}
      width={'fit-content'}
      // bgcolor={"#f3f3f3"}
      borderRadius={12}
      px={12}
      py={24}

    >
      {children}
    </Box>
)
