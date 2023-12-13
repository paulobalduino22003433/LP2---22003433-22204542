package pt.ulusofona.lp2.deisichess;

import java.util.ArrayList;

public class PecaPoneiMagico extends Peca {

    public PecaPoneiMagico(String identificador, String tipoDePeca, String equipa, String alcunha) {
        super(identificador, tipoDePeca, equipa, alcunha);
    }

    @Override
    public void setPng() {
        if (equipa.equals("10")) {
            png = "ponei_magico_black.png";
        } else {
            png = "ponei_magico_white.png";
        }
    }

    @Override
    public StatsPecaException moveOfPiece(String equipe, int x0, int y0, int x1, int y1, ArrayList<String> movimentosParaPeca, ArrayList<Peca> blackTeam, ArrayList<Peca> whiteTeam) throws StatsPecaException {
        String[] partes;

        for (String s : movimentosParaPeca) {
            partes = s.split("/");

            for (Peca pecaPreta : blackTeam) {
                if (x1 > x0 && y1 > y0) {
                    if (pecaPreta.getX().equals(x0 + 1 + "") || pecaPreta.getX().equals(x0 + 2 + "")) {
                        if (pecaPreta.getY().equals(y0 + "")) {
                            for (Peca pretaPeca : blackTeam) {
                                if (pretaPeca.getY().equals(y0 + 1 + "") || pretaPeca.getY().equals(y0 + 2 + "")) {
                                    if (!pecaPreta.getEquipa().equals(equipe) && pecaPreta.getX().equals(x1 + "") && pecaPreta.getY().equals(y1 +"")) {
                                        throw new StatsPecaException("CAPTURE");
                                    }

                                    if (pretaPeca.getX().equals(x0 + "")) {
                                        throw new StatsPecaException("INVALID");
                                    } else if (pretaPeca.getX().equals(x0 + 1 + "") && pretaPeca.getY().equals(y0 + 2 + "")) {
                                        throw new StatsPecaException("INVALID");
                                    }
                                }
                            }

                            throw new StatsPecaException("VALID");

                        } else if (pecaPreta.getY().equals(y0 + 1 + "") && pecaPreta.getX().equals(x0 + 2 + "")) {
                            for (Peca pretaPeca : blackTeam) {
                                if (pretaPeca.getY().equals(y0 + 1 + "") || pretaPeca.getY().equals(y0 + 2 + "")) {
                                    if (!pecaPreta.getEquipa().equals(equipe) && pecaPreta.getX().equals(x1 + "") && pecaPreta.getY().equals(y1 +"")) {
                                        throw new StatsPecaException("CAPTURE");
                                    }

                                    if (pretaPeca.getX().equals(x0 + "")) {
                                        throw new StatsPecaException("INVALID");
                                    } else if (pretaPeca.getX().equals(x0 + 1 + "") && pretaPeca.getY().equals(y0 + 2 + "")) {
                                        throw new StatsPecaException("INVALID");
                                    }
                                }
                            }

                            throw new StatsPecaException("VALID");
                        }
                    }

                    if (partes[0].equals(x1 + "") && partes[1].equals(y1 + "")) {
                        throw new StatsPecaException("VALID");
                    }
                } else if (x1 < x0 && y1 > y0) {
                    if (pecaPreta.getX().equals(x0 - 1 + "") || pecaPreta.getX().equals(x0 - 2 + "")) {
                        if (pecaPreta.getY().equals(y0 + "")) {
                            for (Peca pretaPeca : blackTeam) {
                                if (pretaPeca.getY().equals(y0 + 1 + "") || pretaPeca.getY().equals(y0 + 2 + "")) {
                                    if (!pecaPreta.getEquipa().equals(equipe) && pecaPreta.getX().equals(x1 + "") && pecaPreta.getY().equals(y1 + "")) {
                                        throw new StatsPecaException("CAPTURE");
                                    }

                                    if (pretaPeca.getX().equals(x0 + "")) {
                                        throw new StatsPecaException("INVALID");
                                    } else if (pretaPeca.getX().equals(x0 - 1 + "") && pretaPeca.getY().equals(y0 + 2 + "")) {
                                        throw new StatsPecaException("INVALID");
                                    }
                                }
                            }

                            throw new StatsPecaException("VALID");

                        } else if (pecaPreta.getY().equals(y0 + 1 + "") && pecaPreta.getX().equals(x0 - 2 + "")) {
                            for (Peca pretaPeca : blackTeam) {
                                if (pretaPeca.getY().equals(y0 + 1 + "") || pretaPeca.getY().equals(y0 + 2 + "")) {
                                    if (!pecaPreta.getEquipa().equals(equipe) && pecaPreta.getX().equals(x1 + "") && pecaPreta.getY().equals(y1 + "")) {
                                        throw new StatsPecaException("CAPTURE");
                                    }

                                    if (pretaPeca.getX().equals(x0 + "")) {
                                        throw new StatsPecaException("INVALID");
                                    } else if (pretaPeca.getX().equals(x0 - 1 + "") && pretaPeca.getY().equals(y0 + 2 + "")) {
                                        throw new StatsPecaException("INVALID");
                                    }
                                }
                            }

                            throw new StatsPecaException("VALID");
                        }
                    }

                    if (partes[0].equals(x1 + "") && partes[1].equals(y1 + "")) {
                        throw new StatsPecaException("VALID");
                    }
                } else if (x1 > x0 && y1 < y0) {
                    if (pecaPreta.getX().equals(x0 + 1 + "") || pecaPreta.getX().equals(x0 + 2 + "")) {
                        if (pecaPreta.getY().equals(y0 + "")) {
                            for (Peca pretaPeca : blackTeam) {
                                if (pretaPeca.getY().equals(y0 - 1 + "") || pretaPeca.getY().equals(y0 - 2 + "")) {
                                    if (!pecaPreta.getEquipa().equals(equipe) && pecaPreta.getX().equals(x1 + "") && pecaPreta.getY().equals(y1 + "")) {
                                        throw new StatsPecaException("CAPTURE");
                                    }

                                    if (pretaPeca.getX().equals(x0 + "")) {
                                        throw new StatsPecaException("INVALID");
                                    } else if (pretaPeca.getX().equals(x0 + 1 + "") && pretaPeca.getY().equals(y0 - 2 + "")) {
                                        throw new StatsPecaException("INVALID");
                                    }
                                }
                            }

                            throw new StatsPecaException("VALID");

                        } else if (pecaPreta.getY().equals(y0 - 1 + "") && pecaPreta.getX().equals(x0 + 2 + "")) {
                            for (Peca pretaPeca : blackTeam) {
                                if (pretaPeca.getY().equals(y0 - 1 + "") || pretaPeca.getY().equals(y0 - 2 + "")) {
                                    if (!pecaPreta.getEquipa().equals(equipe) && pecaPreta.getX().equals(x1 + "") && pecaPreta.getY().equals(y1 + "")) {
                                        throw new StatsPecaException("CAPTURE");
                                    }

                                    if (pretaPeca.getX().equals(x0 + "")) {
                                        throw new StatsPecaException("INVALID");
                                    } else if (pretaPeca.getX().equals(x0 + 1 + "") && pretaPeca.getY().equals(y0 - 2 + "")) {
                                        throw new StatsPecaException("INVALID");
                                    }
                                }
                            }

                            throw new StatsPecaException("VALID");
                        }
                    }

                    if (partes[0].equals(x1 + "") && partes[1].equals(y1 + "")) {
                        throw new StatsPecaException("VALID");
                    }
                } else if (x1 < x0 && y1 < y0) {
                    if (pecaPreta.getX().equals(x0 - 1 + "") || pecaPreta.getX().equals(x0 - 2 + "")) {
                        if (pecaPreta.getY().equals(y0 + "")) {
                            for (Peca pretaPeca : blackTeam) {
                                if (pretaPeca.getY().equals(y0 - 1 + "") || pretaPeca.getY().equals(y0 - 2 + "")) {
                                    if (!pecaPreta.getEquipa().equals(equipe) && pecaPreta.getX().equals(x1 + "") && pecaPreta.getY().equals(y1 + "")) {
                                        throw new StatsPecaException("CAPTURE");
                                    }

                                    if (pretaPeca.getX().equals(x0 + "")) {
                                        throw new StatsPecaException("INVALID");
                                    } else if (pretaPeca.getX().equals(x0 - 1 + "") && pretaPeca.getY().equals(y0 - 2 + "")) {
                                        throw new StatsPecaException("INVALID");
                                    }
                                }
                            }

                            throw new StatsPecaException("VALID");

                        } else if (pecaPreta.getY().equals(y0 - 1 + "") && pecaPreta.getX().equals(x0 - 2 + "")) {
                            for (Peca pretaPeca : blackTeam) {
                                if (pretaPeca.getY().equals(y0 - 1 + "") || pretaPeca.getY().equals(y0 - 2 + "")) {
                                    if (!pecaPreta.getEquipa().equals(equipe) && pecaPreta.getX().equals(x1 + "") && pecaPreta.getY().equals(y1 + "")) {
                                        throw new StatsPecaException("CAPTURE");
                                    }

                                    if (pretaPeca.getX().equals(x0 + "")) {
                                        throw new StatsPecaException("INVALID");
                                    } else if (pretaPeca.getX().equals(x0 - 1 + "") && pretaPeca.getY().equals(y0 - 2 + "")) {
                                        throw new StatsPecaException("INVALID");
                                    }
                                }
                            }

                            throw new StatsPecaException("VALID");
                        }
                    }

                    if (partes[0].equals(x1 + "") && partes[1].equals(y1 + "")) {
                        throw new StatsPecaException("VALID");
                    }
                }
            }
            for (Peca pecaBranca : whiteTeam) {
                if (x1 > x0 && y1 > y0) {
                    if (pecaBranca.getX().equals(x0 + 1 + "") || pecaBranca.getX().equals(x0 + 2 + "")) {
                        if (pecaBranca.getY().equals(y0 + "")) {
                            for (Peca brancaPeca : whiteTeam) {
                                if (brancaPeca.getY().equals(y0 + 1 + "") || brancaPeca.getY().equals(y0 + 2 + "")) {
                                    if (!pecaBranca.getEquipa().equals(equipe) && pecaBranca.getX().equals(x1 + "") && pecaBranca.getY().equals(y1 +"")) {
                                        throw new StatsPecaException("CAPTURE");
                                    }

                                    if (brancaPeca.getX().equals(x0 + "")) {
                                        throw new StatsPecaException("INVALID");
                                    } else if (brancaPeca.getX().equals(x0 + 1 + "") && brancaPeca.getY().equals(y0 + 2 + "")) {
                                        throw new StatsPecaException("INVALID");
                                    }
                                }
                            }

                            throw new StatsPecaException("VALID");

                        } else if (pecaBranca.getY().equals(y0 + 1 + "") && pecaBranca.getX().equals(x0 + 2 + "")) {
                            for (Peca pretaPeca : whiteTeam) {
                                if (pretaPeca.getY().equals(y0 + 1 + "") || pretaPeca.getY().equals(y0 + 2 + "")) {
                                    if (!pecaBranca.getEquipa().equals(equipe) && pecaBranca.getX().equals(x1 + "") && pecaBranca.getY().equals(y1 +"")) {
                                        throw new StatsPecaException("CAPTURE");
                                    }

                                    if (pretaPeca.getX().equals(x0 + "")) {
                                        throw new StatsPecaException("INVALID");
                                    } else if (pretaPeca.getX().equals(x0 + 1 + "") && pretaPeca.getY().equals(y0 + 2 + "")) {
                                        throw new StatsPecaException("INVALID");
                                    }
                                }
                            }

                            throw new StatsPecaException("VALID");
                        }
                    }

                    if (partes[0].equals(x1 + "") && partes[1].equals(y1 + "")) {
                        throw new StatsPecaException("VALID");
                    }
                } else if (x1 < x0 && y1 > y0) {
                    if (pecaBranca.getX().equals(x0 - 1 + "") || pecaBranca.getX().equals(x0 - 2 + "")) {
                        if (pecaBranca.getY().equals(y0 + "")) {
                            for (Peca pretaPeca : whiteTeam) {
                                if (pretaPeca.getY().equals(y0 + 1 + "") || pretaPeca.getY().equals(y0 + 2 + "")) {
                                    if (!pecaBranca.getEquipa().equals(equipe) && pecaBranca.getX().equals(x1 + "") && pecaBranca.getY().equals(y1 + "")) {
                                        throw new StatsPecaException("CAPTURE");
                                    }

                                    if (pretaPeca.getX().equals(x0 + "")) {
                                        throw new StatsPecaException("INVALID");
                                    } else if (pretaPeca.getX().equals(x0 - 1 + "") && pretaPeca.getY().equals(y0 + 2 + "")) {
                                        throw new StatsPecaException("INVALID");
                                    }
                                }
                            }

                            throw new StatsPecaException("VALID");

                        } else if (pecaBranca.getY().equals(y0 + 1 + "") && pecaBranca.getX().equals(x0 - 2 + "")) {
                            for (Peca pretaPeca : whiteTeam) {
                                if (pretaPeca.getY().equals(y0 + 1 + "") || pretaPeca.getY().equals(y0 + 2 + "")) {
                                    if (!pecaBranca.getEquipa().equals(equipe) && pecaBranca.getX().equals(x1 + "") && pecaBranca.getY().equals(y1 + "")) {
                                        throw new StatsPecaException("CAPTURE");
                                    }

                                    if (pretaPeca.getX().equals(x0 + "")) {
                                        throw new StatsPecaException("INVALID");
                                    } else if (pretaPeca.getX().equals(x0 - 1 + "") && pretaPeca.getY().equals(y0 + 2 + "")) {
                                        throw new StatsPecaException("INVALID");
                                    }
                                }
                            }

                            throw new StatsPecaException("VALID");
                        }
                    }

                    if (partes[0].equals(x1 + "") && partes[1].equals(y1 + "")) {
                        throw new StatsPecaException("VALID");
                    }
                } else if (x1 > x0 && y1 < y0) {
                    if (pecaBranca.getX().equals(x0 + 1 + "") || pecaBranca.getX().equals(x0 + 2 + "")) {
                        if (pecaBranca.getY().equals(y0 + "")) {
                            for (Peca pretaPeca : whiteTeam) {
                                if (pretaPeca.getY().equals(y0 - 1 + "") || pretaPeca.getY().equals(y0 - 2 + "")) {
                                    if (!pecaBranca.getEquipa().equals(equipe) && pecaBranca.getX().equals(x1 + "") && pecaBranca.getY().equals(y1 + "")) {
                                        throw new StatsPecaException("CAPTURE");
                                    }

                                    if (pretaPeca.getX().equals(x0 + "")) {
                                        throw new StatsPecaException("INVALID");
                                    } else if (pretaPeca.getX().equals(x0 + 1 + "") && pretaPeca.getY().equals(y0 - 2 + "")) {
                                        throw new StatsPecaException("INVALID");
                                    }
                                }
                            }

                            throw new StatsPecaException("VALID");

                        } else if (pecaBranca.getY().equals(y0 - 1 + "") && pecaBranca.getX().equals(x0 + 2 + "")) {
                            for (Peca pretaPeca : whiteTeam) {
                                if (pretaPeca.getY().equals(y0 - 1 + "") || pretaPeca.getY().equals(y0 - 2 + "")) {
                                    if (!pecaBranca.getEquipa().equals(equipe) && pecaBranca.getX().equals(x1 + "") && pecaBranca.getY().equals(y1 + "")) {
                                        throw new StatsPecaException("CAPTURE");
                                    }

                                    if (pretaPeca.getX().equals(x0 + "")) {
                                        throw new StatsPecaException("INVALID");
                                    } else if (pretaPeca.getX().equals(x0 + 1 + "") && pretaPeca.getY().equals(y0 - 2 + "")) {
                                        throw new StatsPecaException("INVALID");
                                    }
                                }
                            }

                            throw new StatsPecaException("VALID");
                        }
                    }

                    if (partes[0].equals(x1 + "") && partes[1].equals(y1 + "")) {
                        throw new StatsPecaException("VALID");
                    }
                } else if (x1 < x0 && y1 < y0) {
                    if (pecaBranca.getX().equals(x0 - 1 + "") || pecaBranca.getX().equals(x0 - 2 + "")) {
                        if (pecaBranca.getY().equals(y0 + "")) {
                            for (Peca pretaPeca : whiteTeam) {
                                if (pretaPeca.getY().equals(y0 - 1 + "") || pretaPeca.getY().equals(y0 - 2 + "")) {
                                    if (!pecaBranca.getEquipa().equals(equipe) && pecaBranca.getX().equals(x1 + "") && pecaBranca.getY().equals(y1 + "")) {
                                        throw new StatsPecaException("CAPTURE");
                                    }

                                    if (pretaPeca.getX().equals(x0 + "")) {
                                        throw new StatsPecaException("INVALID");
                                    } else if (pretaPeca.getX().equals(x0 - 1 + "") && pretaPeca.getY().equals(y0 - 2 + "")) {
                                        throw new StatsPecaException("INVALID");
                                    }
                                }
                            }

                            throw new StatsPecaException("VALID");

                        } else if (pecaBranca.getY().equals(y0 - 1 + "") && pecaBranca.getX().equals(x0 - 2 + "")) {
                            for (Peca pretaPeca : whiteTeam) {
                                if (pretaPeca.getY().equals(y0 - 1 + "") || pretaPeca.getY().equals(y0 - 2 + "")) {
                                    if (!pecaBranca.getEquipa().equals(equipe) && pecaBranca.getX().equals(x1 + "") && pecaBranca.getY().equals(y1 + "")) {
                                        throw new StatsPecaException("CAPTURE");
                                    }

                                    if (pretaPeca.getX().equals(x0 + "")) {
                                        throw new StatsPecaException("INVALID");
                                    } else if (pretaPeca.getX().equals(x0 - 1 + "") && pretaPeca.getY().equals(y0 - 2 + "")) {
                                        throw new StatsPecaException("INVALID");
                                    }
                                }
                            }

                            throw new StatsPecaException("VALID");
                        }
                    }

                    if (partes[0].equals(x1 + "") && partes[1].equals(y1 + "")) {
                        throw new StatsPecaException("VALID");
                    }
                }
            }
        }

        throw new StatsPecaException("INVALID"); //A peça não se pode movimentar para a casa x1 e y1
    }

    @Override
    public String toString() {
        if(estado.equals("capturado")) {
            return identificador + " | Ponei Mágico | 5 | " + equipa + " | " + alcunha + " @ (n/a)";
        }

        return identificador + " | Ponei Mágico | 5 | " + equipa + " | " + alcunha + " @ (" + x + ", " + y + ")";
    }
}
