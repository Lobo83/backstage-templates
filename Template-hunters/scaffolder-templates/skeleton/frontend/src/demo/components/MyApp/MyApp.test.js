import React from 'react';
import '@testing-library/jest-dom/extend-expect'
import { render, screen } from '@testing-library/react';
import MyApp from './MyApp';


test('renders content', () =>{
 const component = render(<MyApp />);
});
