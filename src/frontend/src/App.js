import './App.scss';
import { TeamPageComponent } from './pages/TeamPageComponent';
import {BrowserRouter,Routes,Route} from 'react-router-dom';
import {MatchPageComponent} from './pages/MatchPageComponent';
import { HomePageComponent } from './pages/HomePageComponent';





function App() {
  return (
    <div className="App">
      <BrowserRouter>
     
      <Routes>
         
         <Route  path = "/teams/:teamName/matches/:year" element= {<MatchPageComponent/>}></Route>
         <Route  path  = "/teams/:teamName" element= {<TeamPageComponent/>}></Route>
         <Route exact path = "/" element= {<HomePageComponent/>}></Route>
       
      </Routes>


   
     
     
      </BrowserRouter>
    </div>
  );
}

export default App;
