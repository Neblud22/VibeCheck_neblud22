import './App.css'
import {Navigate, Route, Routes} from "react-router-dom";
import ArtistOverview from "./components/ArtistOverview.tsx";
import EventOverview from "./components/EventOverview.tsx";
import EventDetail from "./components/EventDetail.tsx";
import NavBar from "./components/NavBar.tsx";

function App() {


  return (
    <>
        <NavBar/>
        <Routes>
            <Route path="/" element={<ArtistOverview />} />
            <Route path="/artists" element={<ArtistOverview />} />
            <Route path="/events" element={<EventOverview />} />
            <Route path="/events/artist/:artistId" element={<EventOverview />} />
            <Route path="/events/:eventId" element={<EventDetail />} />
            <Route path="*" element={<Navigate to="/" replace />} />
        </Routes>
    </>
  )
}

export default App
