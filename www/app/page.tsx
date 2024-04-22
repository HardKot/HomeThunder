import { Metadata } from 'next'
import {HomePage} from "@/pages/HomePage";

export const metadata: Metadata = {
  title: 'My Page Title',
}

export default function Page() {
  return <HomePage />
}
