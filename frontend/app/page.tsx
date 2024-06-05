import { Metadata } from 'next'
import {LayoutAppComponent} from "@/shared/components/LayoutAppComponent";
import {AppBar} from "@/widgets/AppBar";

export const metadata: Metadata = {
  title: 'My Page Title',
}

export default function Page() {
  return  <LayoutAppComponent
    header={<AppBar />}
  >
    ...
  </LayoutAppComponent>
}
