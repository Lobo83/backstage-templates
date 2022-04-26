import React from 'react';
import { render, screen } from '@testing-library/react';
import HolaMundo from './HolaMundo';

test('renders learn react link', () => {
  render(<HolaMundo />);
  const linkElement = screen.getByText("HOLA, MUNDO");
  expect(linkElement).toBeInTheDocument();
});
