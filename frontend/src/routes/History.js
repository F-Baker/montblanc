import {createBrowserHistory} from 'history';

// tips on this particular thing
// https://stackoverflow.com/questions/42701129/how-to-push-to-history-in-react-router-v4
// https://github.com/ReactTraining/react-router/blob/master/FAQ.md#how-do-i-access-the-history-object-outside-of-components
const RouterHistory = createBrowserHistory();
export default RouterHistory;